package test.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.HashCodeBuilder;

import test.repository.PersistenceInterceptor;

@MappedSuperclass
@EntityListeners(value = PersistenceInterceptor.class)
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column(nullable = false)
	private String name;

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			// No object to compare
			return false;
		} else if (this == obj) {
			// Same memory instance, must be same object
			return true;
		} else if (this.getClass() != obj.getClass()) {
			// Objects need to be same class to be compared
			return false;
		} else if (this.getId() == 0 || ((BaseEntity) obj).getId() == 0) {
			// At least one of the objects has just been created. Since they are not in the same memory location, they
			// are by definition not equal.
			return this.getName().equals(((BaseEntity) obj).getName());
		} else {
			// Given that the objects are same class, the ID needs to be the same.
			return this.getId() == ((BaseEntity) obj).getId();
		}
	}

	private void getFieldsAsString(StringBuffer sb, Class<?> objClass) {
		sb.append(String.format("\n%s\n", objClass.getSimpleName()));
		Field[] fields = objClass.getDeclaredFields();
		for (Field f : fields) {
			String name = f.getName();
			Object value = "N/A";
			try {
				f.setAccessible(true);
				value = f.get(this);
			} catch (Exception e) {
			}
			if (value instanceof Collection<?>) {
				sb.append(String.format("%s: %s\n", name, "[don't propagate]"));
			} else {
				sb.append(String.format("%s: %s\n", name, value == null ? "N/A" : value.toString()));
			}
		}
	}

	protected final int getId() {
		return id != null ? id.intValue() : 0;
	}

	public String getName() {
		return name;
	}

	public boolean isPersisted() {
		return id != null;
	}

	@Override
	public int hashCode() {
		// Implementing this along the same lines as in AbstractPersistenObject under JDO framework
		// if (getId() == 0) {
		// return super.hashCode();
		// }
		HashCodeBuilder builder = new HashCodeBuilder(13, 27);
		builder.append(this.getClass().getCanonicalName());
		builder.append(getName());
		// builder.append(getId());

		return builder.toHashCode();
	}

	protected void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("\n{");
		Class<?> objClass = this.getClass();
		Class<?> superclass = objClass.getSuperclass();
		while (superclass != Object.class) {
			getFieldsAsString(sb, superclass);
			superclass = superclass.getSuperclass();
		}
		getFieldsAsString(sb, objClass);
		sb.append('}');
		return sb.toString();

	}

}
