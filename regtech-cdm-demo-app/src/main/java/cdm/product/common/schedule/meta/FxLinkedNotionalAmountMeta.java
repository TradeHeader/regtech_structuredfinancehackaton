package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.FxLinkedNotionalAmount;
import cdm.product.common.schedule.validation.FxLinkedNotionalAmountTypeFormatValidator;
import cdm.product.common.schedule.validation.FxLinkedNotionalAmountValidator;
import cdm.product.common.schedule.validation.exists.FxLinkedNotionalAmountOnlyExistsValidator;
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
@RosettaMeta(model=FxLinkedNotionalAmount.class)
public class FxLinkedNotionalAmountMeta implements RosettaMetaData<FxLinkedNotionalAmount> {

	@Override
	public List<Validator<? super FxLinkedNotionalAmount>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FxLinkedNotionalAmount, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FxLinkedNotionalAmount> validator() {
		return new FxLinkedNotionalAmountValidator();
	}

	@Override
	public Validator<? super FxLinkedNotionalAmount> typeFormatValidator() {
		return new FxLinkedNotionalAmountTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FxLinkedNotionalAmount, Set<String>> onlyExistsValidator() {
		return new FxLinkedNotionalAmountOnlyExistsValidator();
	}
}
