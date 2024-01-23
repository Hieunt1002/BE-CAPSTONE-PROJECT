package com.capstoneproject.educonnect.Repository;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.AdminStatisticsByMonthDTO;
import com.capstoneproject.educonnect.DTO.AdminStatisticsByYearDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AdminStatistics {
	
	 @PersistenceContext
	 	private EntityManager entityManager;
	 
	 @Transactional
	 public AdminStatisticsByMonthDTO adminStatisticsByCurrentMonth() {
	     String sql = "SELECT \r\n"
	     		+ "    COALESCE((SELECT COUNT(*) FROM user WHERE role = 1 AND status = 1 AND MONTH(createdate) = MONTH(CURRENT_DATE) AND YEAR(createdate) = YEAR(CURRENT_DATE)), 0) AS TotalStudents,\r\n"
	     		+ "    COALESCE((SELECT COUNT(*) FROM user WHERE role = 2 AND status = 1 AND MONTH(createdate) = MONTH(CURRENT_DATE) AND YEAR(createdate) = YEAR(CURRENT_DATE)), 0) AS TotalTutors,\r\n"
	     		+ "    COALESCE((SELECT COUNT(*) FROM user WHERE role = 3 AND status = 1 AND MONTH(createdate) = MONTH(CURRENT_DATE) AND YEAR(createdate) = YEAR(CURRENT_DATE)), 0) AS TotalStaff,\r\n"
	     		+ "    SUM(\r\n"
	     		+ "    CASE \r\n"
	     		+ "        WHEN d.discountid IS NOT NULL AND b.datepay BETWEEN d.startdate AND d.enddate THEN t.price * (1 - d.discount / 100)\r\n"
	     		+ "        ELSE t.price\r\n"
	     		+ "    END) AS TotalRevenue\r\n"
	     		+ "FROM booking b\r\n"
	     		+ "JOIN tutor t ON b.tutorid = t.tutorid\r\n"
	     		+ "JOIN teach te ON t.tutorid = te.tutorid\r\n"
	     		+ "JOIN classcourse cc ON te.classcourseid = cc.classcourseid and b.classcourseid = cc.classcourseid\r\n"
	     		+ "JOIN course co ON cc.courseid = co.courseid\r\n"
	     		+ "LEFT JOIN discountcourse dc ON co.courseid = dc.courseid \r\n"
	     		+ "LEFT JOIN discount d ON dc.discountid = d.discountid \r\n"
	     		+ "WHERE MONTH(b.datepay) = MONTH(CURRENT_DATE) \r\n"
	     		+ "AND YEAR(b.datepay) = YEAR(CURRENT_DATE)\r\n"
	     		+ "AND b.status = 1;";

	     SQLQuery query = (SQLQuery) entityManager.createNativeQuery(sql)
	             .unwrap(SQLQuery.class)
	             .addScalar("TotalStudents", LongType.INSTANCE)
	             .addScalar("TotalTutors", LongType.INSTANCE)
	             .addScalar("TotalStaff", LongType.INSTANCE)
	             .addScalar("TotalRevenue", LongType.INSTANCE)
	             .setResultTransformer(Transformers.aliasToBean(AdminStatisticsByMonthDTO.class));

	     return (AdminStatisticsByMonthDTO) query.uniqueResult();
	 }
	 
	 @Transactional
	 public AdminStatisticsByMonthDTO adminStatisticsByPreviousMonth() {
		  String sql = "SELECT \r\n"
		  		+ "    COALESCE((SELECT COUNT(*) FROM user WHERE role = 1 AND status = 1 AND MONTH(createdate) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND YEAR(createdate) = YEAR(CURRENT_DATE)), 0) AS TotalStudents,\r\n"
		  		+ "    COALESCE((SELECT COUNT(*) FROM user WHERE role = 2 AND status = 1 AND MONTH(createdate) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND YEAR(createdate) = YEAR(CURRENT_DATE)), 0) AS TotalTutors,\r\n"
		  		+ "    COALESCE((SELECT COUNT(*) FROM user WHERE role = 3 AND status = 1 AND MONTH(createdate) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND YEAR(createdate) = YEAR(CURRENT_DATE)), 0) AS TotalStaff,\r\n"
		  		+ "    SUM(\r\n"
		  		+ "    CASE \r\n"
		  		+ "        WHEN d.discountid IS NOT NULL AND b.datepay BETWEEN d.startdate AND d.enddate THEN t.price * (1 - d.discount / 100)\r\n"
		  		+ "        ELSE t.price\r\n"
		  		+ "    END) AS TotalRevenue\r\n"
		  		+ "FROM booking b\r\n"
		  		+ "JOIN tutor t ON b.tutorid = t.tutorid\r\n"
		  		+ "JOIN teach te ON t.tutorid = te.tutorid\r\n"
		  		+ "JOIN classcourse cc ON te.classcourseid = cc.classcourseid and b.classcourseid = cc.classcourseid\r\n"
		  		+ "JOIN course co ON cc.courseid = co.courseid\r\n"
		  		+ "LEFT JOIN discountcourse dc ON co.courseid = dc.courseid \r\n"
		  		+ "LEFT JOIN discount d ON dc.discountid = d.discountid \r\n"
		  		+ "WHERE MONTH(b.datepay) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) \r\n"
		  		+ "AND YEAR(b.datepay) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH))\r\n"
		  		+ "AND b.status = 1;";
		  
	     SQLQuery query = (SQLQuery) entityManager.createNativeQuery(sql)
	             .unwrap(SQLQuery.class)
	             .addScalar("TotalStudents", LongType.INSTANCE)
	             .addScalar("TotalTutors", LongType.INSTANCE)
	             .addScalar("TotalStaff", LongType.INSTANCE)
	             .addScalar("TotalRevenue", LongType.INSTANCE)
	             .setResultTransformer(Transformers.aliasToBean(AdminStatisticsByMonthDTO.class));

	     return (AdminStatisticsByMonthDTO) query.uniqueResult();
	 }
	 
	 @Transactional
	 public List<AdminStatisticsByYearDTO> adminStatisticsByYear() {
	     String sql = "WITH revenue AS (\r\n"
	     		+ "    SELECT \r\n"
	     		+ "        MONTH(b.datepay) AS Month,\r\n"
	     		+ "        SUM(\r\n"
	     		+ "            CASE \r\n"
	     		+ "                WHEN b.datepay BETWEEN d.startdate AND d.enddate THEN t.price * (1 - d.discount / 100)\r\n"
	     		+ "                ELSE t.price\r\n"
	     		+ "            END\r\n"
	     		+ "        ) AS TotalRevenue,\r\n"
	     		+ "        COALESCE(SUM(pm.money), 0) AS money, \r\n"
	     		+ "        MONTH(pm.datepay) AS Monthpay\r\n"
	     		+ "    FROM booking b  \r\n"
	     		+ "    JOIN tutor t ON b.tutorid = t.tutorid\r\n"
	     		+ "    LEFT JOIN payment pm ON pm.tutorid = t.tutorid \r\n"
	     		+ "    JOIN teach te ON t.tutorid = te.tutorid\r\n"
	     		+ "    JOIN classcourse cc ON te.classcourseid = cc.classcourseid AND b.classcourseid = cc.classcourseid\r\n"
	     		+ "    JOIN course co ON cc.courseid = co.courseid\r\n"
	     		+ "    LEFT JOIN discountcourse dc ON co.courseid = dc.courseid \r\n"
	     		+ "    LEFT JOIN discount d ON dc.discountid = d.discountid \r\n"
	     		+ "    WHERE YEAR(b.datepay) = YEAR(CURRENT_DATE) \r\n"
	     		+ "    GROUP BY MONTH(b.datepay), MONTH(pm.datepay)\r\n"
	     		+ ")\r\n"
	     		+ "\r\n"
	     		+ "SELECT \r\n"
	     		+ "    m.namemonth AS Month,\r\n"
	     		+ "    COALESCE(sum(r.TotalRevenue), 0) AS totalamount,\r\n"
	     		+ "    COALESCE(s.money, 0) AS payfortutor,\r\n"
	     		+ "    COALESCE(sum(r.TotalRevenue) * 0.3, 0) AS profit\r\n"
	     		+ "FROM months m\r\n"
	     		+ "LEFT JOIN revenue r ON m.month = r.Month \r\n"
	     		+ "LEFT JOIN revenue s ON m.month = s.Monthpay\r\n"
	     		+ "group by m.month;";

	     SQLQuery query = (SQLQuery) entityManager.createNativeQuery(sql)
	             .unwrap(SQLQuery.class)
	             .addScalar("Month", StringType.INSTANCE)
	             .addScalar("totalamount", LongType.INSTANCE)
	             .addScalar("payfortutor", LongType.INSTANCE)
	             .addScalar("profit", LongType.INSTANCE)
	             .setResultTransformer(Transformers.aliasToBean(AdminStatisticsByYearDTO.class));

	     return query.list();
	 }

}
