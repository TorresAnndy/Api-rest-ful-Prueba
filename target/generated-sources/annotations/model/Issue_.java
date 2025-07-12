package model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Issue.class)
public abstract class Issue_ {

	public static volatile SingularAttribute<Issue, LocalDate> manageDate;
	public static volatile SingularAttribute<Issue, String> issueStatus;
	public static volatile SingularAttribute<Issue, LocalDate> publishDate;
	public static volatile SingularAttribute<Issue, LocalDate> unpublishDate;
	public static volatile SingularAttribute<Issue, Long> id;

	public static final String MANAGE_DATE = "manageDate";
	public static final String ISSUE_STATUS = "issueStatus";
	public static final String PUBLISH_DATE = "publishDate";
	public static final String UNPUBLISH_DATE = "unpublishDate";
	public static final String ID = "id";

}

