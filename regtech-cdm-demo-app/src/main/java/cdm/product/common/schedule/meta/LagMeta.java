package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.Lag;
import cdm.product.common.schedule.validation.LagTypeFormatValidator;
import cdm.product.common.schedule.validation.LagValidator;
import cdm.product.common.schedule.validation.exists.LagOnlyExistsValidator;
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
@RosettaMeta(model=Lag.class)
public class LagMeta implements RosettaMetaData<Lag> {

	@Override
	public List<Validator<? super Lag>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Lag, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Lag> validator() {
		return new LagValidator();
	}

	@Override
	public Validator<? super Lag> typeFormatValidator() {
		return new LagTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Lag, Set<String>> onlyExistsValidator() {
		return new LagOnlyExistsValidator();
	}
}
