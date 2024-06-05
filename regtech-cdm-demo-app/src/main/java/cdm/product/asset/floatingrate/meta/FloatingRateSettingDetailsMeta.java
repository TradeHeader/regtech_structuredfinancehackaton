package cdm.product.asset.floatingrate.meta;

import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
import cdm.product.asset.floatingrate.validation.FloatingRateSettingDetailsTypeFormatValidator;
import cdm.product.asset.floatingrate.validation.FloatingRateSettingDetailsValidator;
import cdm.product.asset.floatingrate.validation.exists.FloatingRateSettingDetailsOnlyExistsValidator;
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
@RosettaMeta(model=FloatingRateSettingDetails.class)
public class FloatingRateSettingDetailsMeta implements RosettaMetaData<FloatingRateSettingDetails> {

	@Override
	public List<Validator<? super FloatingRateSettingDetails>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingRateSettingDetails, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRateSettingDetails> validator() {
		return new FloatingRateSettingDetailsValidator();
	}

	@Override
	public Validator<? super FloatingRateSettingDetails> typeFormatValidator() {
		return new FloatingRateSettingDetailsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRateSettingDetails, Set<String>> onlyExistsValidator() {
		return new FloatingRateSettingDetailsOnlyExistsValidator();
	}
}
