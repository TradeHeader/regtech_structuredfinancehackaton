package cdm.product.asset.meta;

import cdm.product.asset.InterestShortFall;
import cdm.product.asset.validation.InterestShortFallTypeFormatValidator;
import cdm.product.asset.validation.InterestShortFallValidator;
import cdm.product.asset.validation.exists.InterestShortFallOnlyExistsValidator;
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
@RosettaMeta(model=InterestShortFall.class)
public class InterestShortFallMeta implements RosettaMetaData<InterestShortFall> {

	@Override
	public List<Validator<? super InterestShortFall>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super InterestShortFall, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super InterestShortFall> validator() {
		return new InterestShortFallValidator();
	}

	@Override
	public Validator<? super InterestShortFall> typeFormatValidator() {
		return new InterestShortFallTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super InterestShortFall, Set<String>> onlyExistsValidator() {
		return new InterestShortFallOnlyExistsValidator();
	}
}
