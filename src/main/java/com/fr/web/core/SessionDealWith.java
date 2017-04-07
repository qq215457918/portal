package com.fr.web.core;

import com.fr.base.ConfigManager;
import com.fr.base.FRContext;
import com.fr.file.ClusterConfigManager;
import com.fr.general.Inter;
import com.fr.json.JSONArray;
import com.fr.json.JSONException;
import com.fr.json.JSONObject;
import com.fr.script.Calculator;
import com.fr.stable.StableUtils;
import com.fr.stable.StringUtils;
import com.fr.stable.html.Tag;
import com.fr.stable.html.TextHtml;
import com.fr.stable.web.SessionProvider;
import com.fr.stable.web.Weblet;
import com.fr.web.NoPrivilegeException;
import com.fr.web.WebletException;
import com.fr.web.core.A.aB;
import com.fr.web.factory.WebletFactory;
import com.fr.web.utils.WebUtils;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionDealWith
{
  public static Timer SESSION_DEAL_WITH_TIMER = null;
  private static boolean registed = false;
  private static Random sessionIDRandom = null;
  private static int MAXADDRESS_COUNT = 2;
  private static final long __INIT_TIME__ = System.currentTimeMillis();
  private static final Object SESSION_ID_MAP_LOCK;
  private static Map<String, SessionProvider> sessionIDMap;
  private static final Object ADDRESS_MANAGER_LOCK;
  private static ConcurrentHashMap<String, Object> addressManager;

  private static void authenticateLicense()
  {
    StableUtils.retryLicLock();
    byte[] arrayOfByte = StableUtils.getBytes();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      StableUtils.decode(arrayOfByte, localByteArrayOutputStream);
    }
    catch (Exception localException1)
    {
    }
    try
    {
      JSONObject localJSONObject = new JSONObject(new String(localByteArrayOutputStream.toByteArray(), "UTF-8"));
      if (localJSONObject.getLong("DEADLINE") > Calendar.getInstance().getTimeInMillis())
      {
        registed = true;
        int i = 0;
        if (localJSONObject.has("CONCURRENCY"))
          i = localJSONObject.getInt("CONCURRENCY");
        MAXADDRESS_COUNT = i <= 0 ? 2147483647 : i;
      } 
    }
    catch (Exception localException2)
    {
    }
    // 永久
    registed = true;
    MAXADDRESS_COUNT = 2147483647;
  }

  public static String getOrGenerateSessionIDWithCheckRegister(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws Exception
  {
    String str = WebUtils.getHTTPRequestParameter(paramHttpServletRequest, "sessionID");
    if (str == null)
      str = generateSessionIDWithCheckRegister(paramHttpServletRequest, paramHttpServletResponse);
    return str;
  }

  public static String generateSessionIDWithCheckRegister(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws Exception
  {
    Weblet localWeblet;
    if (generateSessionID_isPromptRegisted(paramHttpServletRequest))
      localWeblet = WebletFactory.createEmbeddedWeblet("/com/fr/web/tpl/lic.frm");
    else
      try
      {
        localWeblet = WebletFactory.createWebletByRequest(paramHttpServletRequest, paramHttpServletResponse);
      }
      catch (NoPrivilegeException localNoPrivilegeException)
      {
        return null;
      }
    return generateSessionID(paramHttpServletRequest, paramHttpServletResponse, localWeblet);
  }

  public static String generateSessionID(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Weblet paramWeblet)
    throws Exception
  {
    if (paramWeblet == null)
      throw new WebletException("Need to specify parameter \"reportlet\" or \"resultlet\" or \"formllet\".");
    String str1 = WebUtils.getIpAddr(paramHttpServletRequest);
    Map localMap = WebUtils.parameters4SessionIDInforContainMPCache(paramHttpServletRequest);
    String str2 = WebUtils.getHTTPRequestParameter(paramHttpServletRequest, "op");
    if (((str2 == null) || (paramWeblet.isSessionOccupy())) && (generateSessionID_dealWithOverFlow(paramHttpServletRequest, paramHttpServletResponse, str1)))
      return null;
    SessionProvider localSessionProvider = null;
    if (paramWeblet != null)
      localSessionProvider = paramWeblet.createSessionIDInfor(paramHttpServletRequest, str1, localMap);
    return generateSessionID_addSessionIDInfor(localSessionProvider, str1, paramWeblet);
  }

  private static String generateSessionID_addSessionIDInfor(SessionProvider paramSessionProvider, String paramString, Weblet paramWeblet)
    throws Exception
  {
    String str = addSessionIDInfor(paramSessionProvider);
    if (paramWeblet.isSessionOccupy())
    {
      Object localObject = new HashSet();
      Set localSet = (Set)addressManager.putIfAbsent(paramString, localObject);
      localObject = localSet == null ? localObject : localSet;
      ((Set)localObject).add(str);
    }
    return (String)str;
  }

  private static boolean generateSessionID_dealWithOverFlow(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString)
    throws Exception
  {
    if (isAddressOverFlow(paramString))
      try
      {
        FRContext.getLogger().error(Inter.getLocText("NS_register_ipFull"));
        WebUtils.getPageWhenOverflow(paramHttpServletResponse, addressManager.keySet());
        return true;
      }
      catch (Exception localException)
      {
        throw new Exception(Inter.getLocText("EX-IP_Address_Overflow"));
      }
    return false;
  }

  private static boolean isAddressOverFlow(String paramString)
  {
    return (MAXADDRESS_COUNT < addressManager.size()) && ((StringUtils.isBlank(paramString)) || (!addressManager.containsKey(paramString)));
  }

  public static boolean generateSessionID_isPromptRegisted(HttpServletRequest paramHttpServletRequest)
  {
    if (generateSessionID_checkReg(paramHttpServletRequest))
      return false;
    return Math.random() * 10.0D <= (System.currentTimeMillis() - __INIT_TIME__) / 3600000L / 24L + 1L;
  }

  private static boolean generateSessionID_checkReg(HttpServletRequest paramHttpServletRequest)
  {
    return (registed) || (isLocalHost(paramHttpServletRequest));
  }

  public static void writeRegistrationDiv(HttpServletRequest paramHttpServletRequest, PrintWriter paramPrintWriter)
  {
    if (!generateSessionID_checkReg(paramHttpServletRequest))
    {
      Tag localTag = new Tag("div");
      localTag.css("left", "2px");
      localTag.css("bottom", "36px");
      localTag.css("font", "normal bolder 10pt Arial");
      localTag.css("position", "absolute");
      localTag.sub(new TextHtml("<a href='http://www.finereporthelp.com/help/9/11.html' target='_blank'>" + Inter.getLocText("Registration-Please_Purchase") + "</a>"));
      localTag.writeHtml(paramPrintWriter);
    }
  }

  protected static String addSessionIDInfor(SessionProvider paramSessionProvider)
    throws Exception
  {
    String str = null;
    synchronized (SESSION_ID_MAP_LOCK)
    {
      if (paramSessionProvider != null)
      {
        for (str = randomSessionID(); sessionIDMap.containsKey(str); str = randomSessionID());
        sessionIDMap.put(str, paramSessionProvider);
        Calculator.putThreadSavedNameSpace(SessionIDInfor.asNameSpace(str));
        paramSessionProvider.setSessionID(str);
      }
    }
    return str;
  }

  private static boolean isLocalHost(HttpServletRequest paramHttpServletRequest)
  {
    String str = WebUtils.getIpAddr(paramHttpServletRequest);
    return WebUtils.isLocalHost(str);
  }

  private static String randomSessionID()
  {
    if (sessionIDRandom == null)
      sessionIDRandom = new Random();
    String str = String.valueOf(sessionIDRandom.nextInt(100000));
    if (ClusterConfigManager.getInstance().isUseCluster())
      str = aB.E(str);
    return str;
  }

  public static void closeSession(Object paramObject)
  {
    FRContext.getLogger().info(Inter.getLocText("LOG-Close_Session") + ":" + paramObject);
    SessionIDInfor localSessionIDInfor = null;
    synchronized (SESSION_ID_MAP_LOCK)
    {
      localSessionIDInfor = (SessionIDInfor)sessionIDMap.remove(paramObject);
    }
    if (localSessionIDInfor != null)
    {
      localSessionIDInfor.release();
      // TODO key
      String key = localSessionIDInfor.getRemoteAddress();
      synchronized (ADDRESS_MANAGER_LOCK)
      {
        Set localSet = (Set)addressManager.get(key);
        if (localSet != null)
          localSet.remove(paramObject);
        if ((localSet == null) || (localSet.size() == 0))
          addressManager.remove(key);
      }
    }
  }

  public static void writeSessionTimeout(HttpServletResponse paramHttpServletResponse)
    throws Exception
  {
    WebUtils.dealWithTemplate("/com/fr/web/core/session.html", paramHttpServletResponse);
  }

  public static void responseSessionID(String paramString, HttpServletResponse paramHttpServletResponse)
    throws Exception
  {
    if ((paramString != null) && (!hasSessionID(paramString)))
      paramString = null;
    if (paramString != null)
    {
      PrintWriter localPrintWriter = WebUtils.createPrintWriter(paramHttpServletResponse);
      localPrintWriter.write(paramString);
      localPrintWriter.flush();
      localPrintWriter.close();
    }
  }

  public static void updateSessionID(String paramString)
  {
    if (StringUtils.isBlank(paramString))
      return;
    SessionIDInfor localSessionIDInfor = null;
    synchronized (SESSION_ID_MAP_LOCK)
    {
      localSessionIDInfor = (SessionIDInfor)sessionIDMap.get(paramString);
    }
    if (localSessionIDInfor != null)
      localSessionIDInfor.updateTime();
  }

  public static SessionIDInfor getSessionIDInfor(String paramString)
  {
    if (StringUtils.isBlank(paramString))
      return null;
    synchronized (SESSION_ID_MAP_LOCK)
    {
      return (SessionIDInfor)sessionIDMap.get(paramString);
    }
  }

  public static boolean hasSessionID(String paramString)
  {
    synchronized (SESSION_ID_MAP_LOCK)
    {
      return sessionIDMap.containsKey(paramString);
    }
  }

  public static void viewSessions(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws Exception
  {
    JSONArray localJSONArray = new JSONArray();
    synchronized (SESSION_ID_MAP_LOCK)
    {
      Iterator localIterator = sessionIDMap.values().iterator();
      while (localIterator.hasNext())
      {
        SessionIDInfor localSessionIDInfor = (SessionIDInfor)localIterator.next();
        localJSONArray.put(new JSONObject().put("startTime", new Date(localSessionIDInfor.getStartTime()).toLocaleString()).put("name", localSessionIDInfor.getWebTitle()).put("ip", localSessionIDInfor.getRemoteAddress()).put("user", localSessionIDInfor.getParameterValue("fr_username")));
      }
    }
    // TODO printWriter
    PrintWriter printWriter = WebUtils.createPrintWriter(paramHttpServletResponse);
    ((PrintWriter)printWriter).print(localJSONArray);
    ((PrintWriter)printWriter).flush();
    ((PrintWriter)printWriter).close();
  }

  public static List getVisitInforFromSession()
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    synchronized (SESSION_ID_MAP_LOCK)
    {
      Iterator localIterator = sessionIDMap.values().iterator();
      while (localIterator.hasNext())
      {
        SessionIDInfor localSessionIDInfor = (SessionIDInfor)localIterator.next();
        localArrayList.add(new JSONObject().put("startTime", new Date(localSessionIDInfor.getStartTime()).toLocaleString()).put("name", localSessionIDInfor.getWebTitle()).put("ip", localSessionIDInfor.getRemoteAddress()).put("user", localSessionIDInfor.getParameterValue("fr_username") == null ? Inter.getLocText(new String[] { "Not_Logged_In", "User" }) : localSessionIDInfor.getParameterValue("fr_username")));
      }
    }
    return localArrayList;
  }

  private static void removeTimeoutSessions()
  {
      synchronized (SESSION_ID_MAP_LOCK)
      {
        if (sessionIDMap == null)
          return;
        SessionIDInfor[] arrayOfSessionIDInfor = (SessionIDInfor[])(SessionIDInfor[])sessionIDMap.values().toArray(new SessionIDInfor[0]);
        
        for (int i = 0; i < arrayOfSessionIDInfor.length; i++)
        {
          final SessionIDInfor localSessionIDInfor = arrayOfSessionIDInfor[i];
          if (!localSessionIDInfor.isTimeout())
            continue;
          new Thread(new Runnable() {
              
              @Override
            public void run() {
                SessionDealWith.closeSession(localSessionIDInfor.getSessionID());
            }

          }).start();
        }
      }
  }

  public static void setSessionIDRandom(Random paramRandom)
  {
    sessionIDRandom = paramRandom;
  }

  static
  {
    authenticateLicense();
    SESSION_DEAL_WITH_TIMER = new Timer();
    SESSION_DEAL_WITH_TIMER.schedule(new TimerTask()
    {
      public void run() {
        if (ConfigManager.getInstance().isLicUseLock())
            SessionDealWith.authenticateLicense();
            SessionDealWith.removeTimeoutSessions();
      }
    }
    , ConfigManager.getInstance().getSessionDeadCheckTime(), ConfigManager.getInstance().getSessionDeadCheckTime());
    SESSION_ID_MAP_LOCK = new Object();
    sessionIDMap = new Hashtable();
    ADDRESS_MANAGER_LOCK = new Object();
    addressManager = new ConcurrentHashMap();
  }
}
