package com.effigo.learning.portal.entity;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.NativeQuery;

public class CustomCourseIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		int rowCount;
		NativeQuery<Number> query = session
				.createNativeQuery("SELECT MAX(CAST(SUBSTRING(course_id, 8) AS int)) FROM course_entity");
		Number count = query.uniqueResult();
		rowCount = count != null ? count.intValue() : 0;
		return "course-0" + (++rowCount);
	}
}

class CustomCatIdGenerator1 implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		int rowCount;
		NativeQuery<Number> query = session
				.createNativeQuery("SELECT MAX(CAST(SUBSTRING(category_id,5)AS int) FROM category_entity");
		Number count = query.uniqueResult();
		rowCount = count != null ? count.intValue() : 0;
		return "cat-" + (++rowCount);
	}

}
