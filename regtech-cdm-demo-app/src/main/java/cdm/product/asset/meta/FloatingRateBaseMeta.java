package cdm.product.asset.meta;

import cdm.product.asset.FloatingRateBase;
import cdm.product.asset.validation.FloatingRateBaseTypeFormatValidator;
import cdm.product.asset.validation.FloatingRateBaseValidator;
import cdm.product.asset.validation.exists.FloatingRateBaseOnlyExistsValidator;
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
@RosettaMeta(model=FloatingRateBase.class)
public class FloatingRateBaseMeta implements RosettaMetaData<FloatingRateBase> {

	@Override
	public List<Validator<? super FloatingRateBase>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingRateBase, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRateBase> validator() {
		return new FloatingRateBaseValidator();
	}

	@Override
	public Validator<? super FloatingRateBase> typeFormatValidator() {
		return new FloatingRateBaseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRateBase, Set<String>> onlyExistsValidator() {
		return new FloatingRateBaseOnlyExistsValidator();
	}
}
