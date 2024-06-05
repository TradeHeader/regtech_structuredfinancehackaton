package cdm.product.template.meta;

import cdm.product.template.BasketConstituent;
import cdm.product.template.validation.BasketConstituentTypeFormatValidator;
import cdm.product.template.validation.BasketConstituentValidator;
import cdm.product.template.validation.exists.BasketConstituentOnlyExistsValidator;
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
@RosettaMeta(model=BasketConstituent.class)
public class BasketConstituentMeta implements RosettaMetaData<BasketConstituent> {

	@Override
	public List<Validator<? super BasketConstituent>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.ProductOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super BasketConstituent, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BasketConstituent> validator() {
		return new BasketConstituentValidator();
	}

	@Override
	public Validator<? super BasketConstituent> typeFormatValidator() {
		return new BasketConstituentTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BasketConstituent, Set<String>> onlyExistsValidator() {
		return new BasketConstituentOnlyExistsValidator();
	}
}
