------------------------------
-- 创建获取顶级机构ID的函数
------------------------------
CREATE DEFINER=`root`@`localhost` FUNCTION `getOrganiId`(`dept_id` varchar(50)) RETURNS varchar(50) CHARSET utf8 COLLATE utf8_bin
    COMMENT '根据部门ID获取机构ID'
BEGIN
		DECLARE company_id VARCHAR(50);
		DECLARE department_id VARCHAR(50);
		DECLARE par_dept_id VARCHAR(50);
		
		SELECT d.parents_id INTO department_id from group_info d where d.id = dept_id;

		IF (department_id != '') THEN
			SELECT di.parents_id INTO par_dept_id from group_info di where di.id = department_id;
			IF (par_dept_id != '') THEN
				SET company_id = par_dept_id;
			ELSE
				SET company_id = department_id;
			END IF;
    ELSE
			SET company_id = dept_id;
    END IF;

    RETURN company_id;
END