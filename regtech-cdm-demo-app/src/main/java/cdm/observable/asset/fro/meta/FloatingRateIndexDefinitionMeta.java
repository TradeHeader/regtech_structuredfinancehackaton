package cdm.observable.asset.fro.meta;

import cdm.observable.asset.fro.FloatingRateIndexDefinition;
import cdm.observable.asset.fro.validation.FloatingRateIndexDefinitionTypeFormatValidator;
import cdm.observable.asset.fro.validation.FloatingRateIndexDefinitionValidator;
import cdm.observable.asset.fro.validation.exists.FloatingRateIndexDefinitionOnlyExistsValidator;
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
@RosettaMeta(model=FloatingRateIndexDefinition.class)
public class FloatingRateIndexDefinitionMeta implements RosettaMetaData<FloatingRateIndexDefinition> {

	@Override
	public List<Validator<? super FloatingRateIndexDefinition>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingRateIndexDefinition, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRateIndexDefinition> validator() {
		return new FloatingRateIndexDefinitionValidator();
	}

	@Override
	public Validator<? super FloatingRateIndexDefinition> typeFormatValidator() {
		return new FloatingRateIndexDefinitionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRateIndexDefinition, Set<String>> onlyExistsValidator() {
		return new FloatingRateIndexDefinitionOnlyExistsValidator();
	}
}
