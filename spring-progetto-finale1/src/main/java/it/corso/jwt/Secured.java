package it.corso.jwt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.ws.rs.NameBinding;

@NameBinding //indica che la tipologia di annotation personalizzata puo essere utilizzata come una annotation di legatura di un elemento gia'esistente di JAX RS
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD}) //indica dove possiamo usare l`annotation
public @interface Secured {
	
	String role() default "all"; //definizione membro role dentro annotation
}
