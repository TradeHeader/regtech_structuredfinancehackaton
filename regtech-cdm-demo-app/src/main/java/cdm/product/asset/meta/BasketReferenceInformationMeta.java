package cdm.product.asset.meta;

import cdm.product.asset.BasketReferenceInformation;
import cdm.product.asset.validation.BasketReferenceInformationTypeFormatValidator;
import cdm.product.asset.validation.BasketReferenceInformationValidator;
import cdm.product.asset.validation.exists.BasketReferenceInformationOnlyExistsValidator;
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
@RosettaMeta(model=BasketReferenceInformation.class)
public class BasketReferenceInformationMeta implements RosettaMetaData<BasketReferenceInformation> {

	@Override
	public List<Validator<? super BasketReferenceInformation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.BasketReferenceInformationBasketReferenceInformationChoice.class),
			factory.create(cdm.product.asset.validation.datarule.BasketReferenceInformationNthToDefault.class),
			factory.create(cdm.product.asset.validation.datarule.BasketReferenceInformationMthToDefault.class)
		);
	}
	
	@Override
	public List<Function<? super BasketReferenceInformation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BasketReferenceInformation> validator() {
		return new BasketReferenceInformationValidator();
	}

	@Override
	public Validator<? super BasketReferenceInformation> typeFormatValidator() {
		return new BasketReferenceInformationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BasketReferenceInformation, Set<String>> onlyExistsValidator() {
		return new BasketReferenceInformationOnlyExistsValidator();
	}
}
