package com.jajm.inquilino.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.jajm.inquilino.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.jajm.inquilino.thymeleaf.processor.MenuAttributeTagProcessor;
import com.jajm.inquilino.thymeleaf.processor.MessageElementTagProcessor;
import com.jajm.inquilino.thymeleaf.processor.OrderElementTagProcessor;
import com.jajm.inquilino.thymeleaf.processor.PaginationElementTagProcessor;

public class JajmDialect extends AbstractProcessorDialect {

	public JajmDialect() {
		super("Jajm Inquilino", "jajm", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
