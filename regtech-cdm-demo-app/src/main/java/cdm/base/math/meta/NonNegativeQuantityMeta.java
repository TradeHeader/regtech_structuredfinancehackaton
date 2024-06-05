package cdm.base.math.meta;

import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.validation.NonNegativeQuantityTypeFormatValidator;
import cdm.base.math.validation.NonNegativeQuantityValidator;
import cdm.base.math.validation.exists.NonNegativeQuantityOnlyExistsValidator;
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
@RosettaMeta(model=NonNegativeQuantity.class)
public class NonNegativeQuantityMeta implements RosettaMetaData<NonNegativeQuantity> {

	@Override
	public List<Validator<? super NonNegativeQuantity>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.math.validation.datarule.NonNegativeQuantityNonNegativeQuantityAmount.class),
			factory.create(cdm.base.math.validation.datarule.QuantityAmountOnlyExists.class),
			factory.create(cdm.base.math.validation.datarule.QuantityScheduleQuantityMultiplier.class),
			factory.create(cdm.base.math.validation.datarule.QuantityScheduleUnitOfAmountExists.class),
			factory.create(cdm.base.math.validation.datarule.MeasureScheduleValueExists.class)
		);
	}
	
	@Override
	public List<Function<? super NonNegativeQuantity, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super NonNegativeQuantity> validator() {
		return new NonNegativeQuantityValidator();
	}

	@Override
	public Validator<? super NonNegativeQuantity> typeFormatValidator() {
		return new NonNegativeQuantityTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super NonNegativeQuantity, Set<String>> onlyExistsValidator() {
		return new NonNegativeQuantityOnlyExistsValidator();
	}
}
