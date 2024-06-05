package cdm.product.template.meta;

import cdm.product.template.ConstituentWeight;
import cdm.product.template.validation.ConstituentWeightTypeFormatValidator;
import cdm.product.template.validation.ConstituentWeightValidator;
import cdm.product.template.validation.exists.ConstituentWeightOnlyExistsValidator;
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
@RosettaMeta(model=ConstituentWeight.class)
public class ConstituentWeightMeta implements RosettaMetaData<ConstituentWeight> {

	@Override
	public List<Validator<? super ConstituentWeight>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.ConstituentWeightBasketPercentage.class)
		);
	}
	
	@Override
	public List<Function<? super ConstituentWeight, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ConstituentWeight> validator() {
		return new ConstituentWeightValidator();
	}

	@Override
	public Validator<? super ConstituentWeight> typeFormatValidator() {
		return new ConstituentWeightTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ConstituentWeight, Set<String>> onlyExistsValidator() {
		return new ConstituentWeightOnlyExistsValidator();
	}
}
