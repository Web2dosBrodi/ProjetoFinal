package Model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Evento.class)
public abstract class Evento_ {

	public static volatile SingularAttribute<Evento, Date> data;
	public static volatile SingularAttribute<Evento, String> nome;
	public static volatile SingularAttribute<Evento, Long> id;
	public static volatile SingularAttribute<Evento, Integer> userId;

}

