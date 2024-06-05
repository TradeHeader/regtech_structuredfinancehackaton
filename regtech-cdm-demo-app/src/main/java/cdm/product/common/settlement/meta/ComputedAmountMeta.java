package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.ComputedAmount;
import cdm.product.common.settlement.validation.ComputedAmountTypeFormatValidator;
import cdm.product.common.settlement.validation.ComputedAmountValidator;
import cdm.product.common.settlement.validation.exists.ComputedAmountOnlyExistsValidator;
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
@RosettaMeta(model=ComputedAmount.class)
public class ComputedAmountMeta implements RosettaMetaData<ComputedAmount> {

	@Override
	public List<Validator<? super ComputedAmount>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ComputedAmount, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ComputedAmount> validator() {
		return new ComputedAmountValidator();
	}

	@Override
	public Validator<? super ComputedAmount> typeFormatValidator() {
		return new ComputedAmountTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ComputedAmount, Set<String>> onlyExistsValidator() {
		return new ComputedAmountOnlyExistsValidator();
	}
}
