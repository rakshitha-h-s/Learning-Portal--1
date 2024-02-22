package com.effigo.learning.portal.entity;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.NativeQuery;

public class CustomCourseIdGenerator implements IdentifierGenerator {

	private static int rowCount = 0;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		if (rowCount == 0) {
			NativeQuery<Number> query = session.createNativeQuery("SELECT COUNT(*) FROM course_entity");
			Number count = query.uniqueResult();
			rowCount = count != null ? count.intValue() : 0;
		}
		return "course-" + (++rowCount);
	}
}

class CustomCatIdGenerator1 implements IdentifierGenerator {

	private static int count = 0;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		if (count == 0) {
			NativeQuery<Number> query = session.createNativeQuery("SELECT COUNT(*) FROM category_entity");
			Number count = query.uniqueResult();
			count = count != null ? count.intValue() : 0;
		}
		return "cat-0" + (++count);
	}

}
