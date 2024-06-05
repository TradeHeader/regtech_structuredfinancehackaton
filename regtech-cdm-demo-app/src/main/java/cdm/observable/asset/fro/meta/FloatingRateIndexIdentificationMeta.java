package cdm.observable.asset.fro.meta;

import cdm.observable.asset.fro.FloatingRateIndexIdentification;
import cdm.observable.asset.fro.validation.FloatingRateIndexIdentificationTypeFormatValidator;
import cdm.observable.asset.fro.validation.FloatingRateIndexIdentificationValidator;
import cdm.observable.asset.fro.validation.exists.FloatingRateIndexIdentificationOnlyExistsValidator;
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
@RosettaMeta(model=FloatingRateIndexIdentification.class)
public class FloatingRateIndexIdentificationMeta implements RosettaMetaData<FloatingRateIndexIdentification> {

	@Override
	public List<Validator<? super FloatingRateIndexIdentification>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingRateIndexIdentification, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRateIndexIdentification> validator() {
		return new FloatingRateIndexIdentificationValidator();
	}

	@Override
	public Validator<? super FloatingRateIndexIdentification> typeFormatValidator() {
		return new FloatingRateIndexIdentificationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRateIndexIdentification, Set<String>> onlyExistsValidator() {
		return new FloatingRateIndexIdentificationOnlyExistsValidator();
	}
}
