package com.fr.web.core;

import com.fr.base.FRContext;
import com.fr.base.IconManager;
import com.fr.file.ClusterConfigManager;
import com.fr.general.ComparatorUtils;
import com.fr.general.VT4FR;
import com.fr.script.Calculator;
import com.fr.stable.ArrayUtils;
import com.fr.stable.StableUtils;
import com.fr.stable.fun.Service;
import com.fr.stable.script.NameSpace;
import com.fr.stable.web.Weblet;
import com.fr.web.NoPrivilegeException;
import com.fr.web.core.A.PA;
import com.fr.web.core.A.WB;
import com.fr.web.core.A.aB;
import com.fr.web.core.A.dB;
import com.fr.web.core.A.l;
import com.fr.web.core.A.o;
import com.fr.web.core.A.pA;
import com.fr.web.core.A.yA;
import com.fr.web.factory.WebletFactory;
import com.fr.web.utils.WebUtils;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportDispatcher {
	private static Service[] servicesAvailable = { WB.G(), yA.O(), l.E(), o.J(), dB.V(), PA.M(),
			IconManager.getIconManager() };

	public static synchronized void registerGroupService(Service[] paramArrayOfService) {
		if ((ArrayUtils.isEmpty(paramArrayOfService)) && (servicesAvailable != null))
			return;
		if (servicesAvailable == null)
			servicesAvailable = new Service[0];
		int i = servicesAvailable.length;
		int j = paramArrayOfService.length;
		Service[] arrayOfService = new Service[i + j];
		System.arraycopy(servicesAvailable, 0, arrayOfService, 0, i);
		for (int k = 0; k < j; k++)
			arrayOfService[(i + k)] = paramArrayOfService[k];
		servicesAvailable = arrayOfService;
	}

	public static void dealWithRequest(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		String str1 = WebUtils.getHTTPRequestParameter(paramHttpServletRequest, "op");
		String str2 = WebUtils.getHTTPRequestParameter(paramHttpServletRequest, "sessionID");
		if (ClusterConfigManager.getInstance().isUseCluster()) {
			if (aB.A(paramHttpServletRequest, paramHttpServletResponse, str1, str2))
				return;
			if (ComparatorUtils.equals(str1, "cluster_share"))
				pA.N().process(paramHttpServletRequest, paramHttpServletResponse, str1);
		}
		if (("closesessionid".equalsIgnoreCase(str1)) && (str2 != null)) {
			SessionDealWith.closeSession(str2);
			return;
		}
		if (str2 != null) {
			SessionDealWith.updateSessionID(str2);
			NameSpace localNameSpace = SessionIDInfor.asNameSpace(str2);
			Calculator.putThreadSavedNameSpace(localNameSpace);
		}
		if ("getSessionID".equalsIgnoreCase(str1)) {
			if (str2 == null)
				str2 = SessionDealWith.generateSessionIDWithCheckRegister(paramHttpServletRequest,
						paramHttpServletResponse);
			SessionDealWith.responseSessionID(str2, paramHttpServletResponse);
			return;
		}
		if ((str2 != null) && (!SessionDealWith.hasSessionID(str2))) {
			SessionDealWith.writeSessionTimeout(paramHttpServletResponse);
			return;
		}
		if ((ClusterConfigManager.getInstance().isUseCluster())
				&& (aB.A(paramHttpServletRequest, paramHttpServletResponse, str1, str2)))
			return;
		dealWeblet(str1, str2, paramHttpServletRequest, paramHttpServletResponse);
	}

	private static void dealWeblet(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		Weblet localWeblet;
		try {
			localWeblet = WebletFactory.createWebletByRequest(paramHttpServletRequest, paramHttpServletResponse);
		} catch (NoPrivilegeException localNoPrivilegeException) {
			return;
		}
		if (localWeblet != null) {
			if (SessionDealWith.generateSessionID_isPromptRegisted(paramHttpServletRequest))
				localWeblet = WebletFactory.createEmbeddedWeblet("/com/fr/web/tpl/lic.frm");
			localWeblet.dealWeblet(paramHttpServletRequest, paramHttpServletResponse);
			return;
		}
		if (paramString1 != null)
			dealWithOp(paramString1, paramString2, paramHttpServletRequest, paramHttpServletResponse);
		else
			dealWithoutOp(paramHttpServletRequest, paramHttpServletResponse);
	}

	private static void dealWithoutOp(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		if (WebUtils.getHTTPRequestParameter(paramHttpServletRequest, "sessionID") != null)
			return;
		HashMap<String, Serializable> localHashMap = new HashMap<String, Serializable>();
		if ((VT4FR.isLicAvailable(StableUtils.getBytes())) && (VT4FR.FS_BI.support()))
			localHashMap.put("isSupportFS", "true");
		localHashMap.put("__locale__", paramHttpServletRequest.getLocale());
		if (FRContext.isShouldForceEnglish())
			WebUtils.writeOutTemplate("/com/fr/web/core/deploySuccess_en_us.html", paramHttpServletResponse,
					localHashMap);
		else
			WebUtils.writeOutTemplate("/com/fr/web/core/deploySuccess.html", paramHttpServletResponse, localHashMap);
	}

	private static void dealWithOp(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		paramString1 = paramString1.toLowerCase();
		paramHttpServletRequest.setAttribute("op", paramString1);
		for (int i = 0; i < servicesAvailable.length; i++) {
			Service localService = servicesAvailable[i];
			String str = localService.actionOP();
			if (!paramString1.equalsIgnoreCase(str))
				continue;
			localService.process(paramHttpServletRequest, paramHttpServletResponse, paramString1, paramString2);
			return;
		}
		PrintWriter localPrintWriter = WebUtils.createPrintWriter(paramHttpServletResponse);
		localPrintWriter.println("Unresolvable Operation:" + StableUtils.replaceScript4Xss(paramString1)
				+ "  in class ReportDispatcher");
		localPrintWriter.flush();
		localPrintWriter.close();
	}
}

/*
 * Location: C:\Documents and Settings\Administrator\桌面\fr\fr-server-7.1.jar
 * Qualified Name: com.fr.web.core.ReportDispatcher JD-Core Version: 0.6.0
 */