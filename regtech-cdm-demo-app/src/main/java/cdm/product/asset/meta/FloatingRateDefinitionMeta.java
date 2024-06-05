package cdm.product.asset.meta;

import cdm.product.asset.FloatingRateDefinition;
import cdm.product.asset.validation.FloatingRateDefinitionTypeFormatValidator;
import cdm.product.asset.validation.FloatingRateDefinitionValidator;
import cdm.product.asset.validation.exists.FloatingRateDefinitionOnlyExistsValidator;
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
@RosettaMeta(model=FloatingRateDefinition.class)
public class FloatingRateDefinitionMeta implements RosettaMetaData<FloatingRateDefinition> {

	@Override
	public List<Validator<? super FloatingRateDefinition>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.FloatingRateDefinitionFloatingRateMultiplier.class)
		);
	}
	
	@Override
	public List<Function<? super FloatingRateDefinition, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRateDefinition> validator() {
		return new FloatingRateDefinitionValidator();
	}

	@Override
	public Validator<? super FloatingRateDefinition> typeFormatValidator() {
		return new FloatingRateDefinitionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRateDefinition, Set<String>> onlyExistsValidator() {
		return new FloatingRateDefinitionOnlyExistsValidator();
	}
}
