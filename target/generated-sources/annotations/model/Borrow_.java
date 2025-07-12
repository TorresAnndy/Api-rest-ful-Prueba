package model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Borrow.class)
public abstract class Borrow_ {

	public static volatile SingularAttribute<Borrow, LocalDate> fromDate;
	public static volatile SingularAttribute<Borrow, String> borrowStatus;
	public static volatile SingularAttribute<Borrow, LocalDate> toDate;
	public static volatile SingularAttribute<Borrow, Long> id;

	public static final String FROM_DATE = "fromDate";
	public static final String BORROW_STATUS = "borrowStatus";
	public static final String TO_DATE = "toDate";
	public static final String ID = "id";

}

