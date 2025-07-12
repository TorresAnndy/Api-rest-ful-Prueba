package model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Publication.class)
public abstract class Publication_ {

	public static volatile SingularAttribute<Publication, String> statusPublication;
	public static volatile SingularAttribute<Publication, Integer> year;
	public static volatile SingularAttribute<Publication, String> author;
	public static volatile SingularAttribute<Publication, Long> id;
	public static volatile SingularAttribute<Publication, String> title;

	public static final String STATUS_PUBLICATION = "statusPublication";
	public static final String YEAR = "year";
	public static final String AUTHOR = "author";
	public static final String ID = "id";
	public static final String TITLE = "title";

}

