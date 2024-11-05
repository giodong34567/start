package springmvc.starter.demo.repository.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import springmvc.starter.demo.entity.StudentEntity;
import springmvc.starter.demo.repository.custom.StudentRepositoryCustom;
import springmvc.starter.demo.utils.StringUtils;

import java.util.List;
import java.util.Map;

@Repository
@Primary
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StudentEntity> findAllDetails(Map<String, String> params) {
        StringBuilder sql = new StringBuilder("select st.* from student as st");
        joinQuery(params, sql);
        sql.append(" where 1=1");
        whereQueryNormal(params, sql);
        sql.append(" group by st.id");
        Query query = entityManager.createNativeQuery(sql.toString(), StudentEntity.class);
        return query.getResultList();
    }

    public void joinQuery(Map<String, String> params, StringBuilder join) {
        if (params.containsKey("companyName") && StringUtils.checkString(params.get("companyName"))) {
            join.append(" inner join job as j on st.id = j.studentId");
        }
        if ((params.containsKey("schoolId") && StringUtils.checkString(params.get("schoolId"))) ||
                (params.containsKey("majorId") && StringUtils.checkString(params.get("majorId")))) {
            join.append(" inner join graduation as gr on st.id = gr.studentId");
            if (params.containsKey("schoolId") && StringUtils.checkString(params.get("schoolId"))) {
                join.append(" inner join school as sc on gr.schoolId = sc.id");
            }
            if (params.containsKey("majorId") && StringUtils.checkString(params.get("majorId"))) {
                join.append(" inner join major as ma on gr.majorId = ma.id");
            }
        }
    }

    public void whereQueryNormal(Map<String, String> params, StringBuilder where) {
        if (params.containsKey("id") && StringUtils.checkString(params.get("id"))) {
            where.append(" AND st.id = " + params.get("id").toString());
        }
        if (params.containsKey("fullname") && StringUtils.checkString(params.get("fullname"))) {
            where.append(" AND st.fullname like '%" + params.get("fullname").toString() + "%'");
        }
        if (params.containsKey("schoolId") && StringUtils.checkString(params.get("schoolId"))) {
            where.append(" AND sc.schoolId = " + params.get("schoolId").toString());
        }
        if (params.containsKey("majorId") && StringUtils.checkString(params.get("majorId"))) {
            where.append(" AND ma.id = " + params.get("majorId").toString());
        }
        if (params.containsKey("companyName") && StringUtils.checkString(params.get("companyName"))) {
            where.append(" AND companyName like '%" + params.get("companyName").toString() +"%'");
        }
    }
}
