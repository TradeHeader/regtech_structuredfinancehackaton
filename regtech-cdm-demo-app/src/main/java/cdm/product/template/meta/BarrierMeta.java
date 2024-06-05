package cdm.product.template.meta;

import cdm.product.template.Barrier;
import cdm.product.template.validation.BarrierTypeFormatValidator;
import cdm.product.template.validation.BarrierValidator;
import cdm.product.template.validation.exists.BarrierOnlyExistsValidator;
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
@RosettaMeta(model=Barrier.class)
public class BarrierMeta implements RosettaMetaData<Barrier> {

	@Override
	public List<Validator<? super Barrier>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Barrier, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Barrier> validator() {
		return new BarrierValidator();
	}

	@Override
	public Validator<? super Barrier> typeFormatValidator() {
		return new BarrierTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Barrier, Set<String>> onlyExistsValidator() {
		return new BarrierOnlyExistsValidator();
	}
}
