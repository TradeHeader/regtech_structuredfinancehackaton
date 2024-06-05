package cdm.product.template.meta;

import cdm.product.template.CancelableProvisionAdjustedDates;
import cdm.product.template.validation.CancelableProvisionAdjustedDatesTypeFormatValidator;
import cdm.product.template.validation.CancelableProvisionAdjustedDatesValidator;
import cdm.product.template.validation.exists.CancelableProvisionAdjustedDatesOnlyExistsValidator;
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
@RosettaMeta(model=CancelableProvisionAdjustedDates.class)
public class CancelableProvisionAdjustedDatesMeta implements RosettaMetaData<CancelableProvisionAdjustedDates> {

	@Override
	public List<Validator<? super CancelableProvisionAdjustedDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CancelableProvisionAdjustedDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CancelableProvisionAdjustedDates> validator() {
		return new CancelableProvisionAdjustedDatesValidator();
	}

	@Override
	public Validator<? super CancelableProvisionAdjustedDates> typeFormatValidator() {
		return new CancelableProvisionAdjustedDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CancelableProvisionAdjustedDates, Set<String>> onlyExistsValidator() {
		return new CancelableProvisionAdjustedDatesOnlyExistsValidator();
	}
}
