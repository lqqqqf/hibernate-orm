/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.jpa.test.userguide.mapping;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.jpa.test.BaseEntityManagerFunctionalTestCase;

import org.junit.Test;

import static org.hibernate.jpa.test.util.TransactionUtil.doInJPA;

/**
 * @author Vlad Mihalcea
 */
public class DateWithTemporalTimestampTest extends BaseEntityManagerFunctionalTestCase {

	@Override
	protected Class<?>[] getAnnotatedClasses() {
		return new Class<?>[] {
				DateEvent.class
		};
	}

	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			DateEvent dateEvent = new DateEvent( new Date() );
			entityManager.persist( dateEvent );
		} );
	}

	@Entity(name = "DateEvent")
	public static class DateEvent {

		@Id
		@GeneratedValue
		private Long id;

		@Temporal(TemporalType.TIMESTAMP)
		private Date timestamp;

		public DateEvent() {
		}

		public DateEvent(Date timestamp) {
			this.timestamp = timestamp;
		}

		public Long getId() {
			return id;
		}

		public Date getTimestamp() {
			return timestamp;
		}
	}
}
