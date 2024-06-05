package cdm.product.template.meta;

import cdm.product.template.ExtendibleProvisionAdjustedDates;
import cdm.product.template.validation.ExtendibleProvisionAdjustedDatesTypeFormatValidator;
import cdm.product.template.validation.ExtendibleProvisionAdjustedDatesValidator;
import cdm.product.template.validation.exists.ExtendibleProvisionAdjustedDatesOnlyExistsValidator;
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
@RosettaMeta(model=ExtendibleProvisionAdjustedDates.class)
public class ExtendibleProvisionAdjustedDatesMeta implements RosettaMetaData<ExtendibleProvisionAdjustedDates> {

	@Override
	public List<Validator<? super ExtendibleProvisionAdjustedDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ExtendibleProvisionAdjustedDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ExtendibleProvisionAdjustedDates> validator() {
		return new ExtendibleProvisionAdjustedDatesValidator();
	}

	@Override
	public Validator<? super ExtendibleProvisionAdjustedDates> typeFormatValidator() {
		return new ExtendibleProvisionAdjustedDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ExtendibleProvisionAdjustedDates, Set<String>> onlyExistsValidator() {
		return new ExtendibleProvisionAdjustedDatesOnlyExistsValidator();
	}
}
