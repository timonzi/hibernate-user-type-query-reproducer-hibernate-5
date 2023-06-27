package org.acme;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

public class MyStringUserType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{org.hibernate.type.StringType.INSTANCE.getSqlTypeDescriptor().getSqlType()};
    }

    @Override
    public Class returnedClass() {
        return MyStringWrapper.class;
    }

    @Override
    public boolean equals(
            final Object x,
            final Object y
    ) throws HibernateException {
        if (x == null || y == null) {
            return false;
        }

        return x.equals(y);
    }

    @Override
    public int hashCode(final Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(
            final ResultSet rs,
            final String[] names,
            final SharedSessionContractImplementor session,
            final Object owner
    ) throws HibernateException, SQLException {
        return new MyStringWrapper(org.hibernate.type.StringType.INSTANCE.nullSafeGet(rs, names[0], session));
    }

    @Override
    public void nullSafeSet(
            final PreparedStatement st,
            final Object value,
            final int index,
            final SharedSessionContractImplementor session
    ) throws HibernateException, SQLException {
        org.hibernate.type.StringType.INSTANCE.nullSafeSet(st, ((MyStringWrapper) value).getField(), index, session);
    }

    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(
            final Serializable cached,
            final Object owner
    ) throws HibernateException {
        return owner;
    }

    @Override
    public Object replace(
            final Object original,
            final Object target,
            final Object owner
    ) throws HibernateException {
        return original;
    }
}
