package cdm.product.template.meta;

import cdm.product.template.Composite;
import cdm.product.template.validation.CompositeTypeFormatValidator;
import cdm.product.template.validation.CompositeValidator;
import cdm.product.template.validation.exists.CompositeOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=Composite.class)
public class CompositeMeta implements RosettaMetaData<Composite> {

	@Override
	public List<Validator<? super Composite>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Composite, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Composite> validator() {
		return new CompositeValidator();
	}

	@Override
	public Validator<? super Composite> typeFormatValidator() {
		return new CompositeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Composite, Set<String>> onlyExistsValidator() {
		return new CompositeOnlyExistsValidator();
	}
}
