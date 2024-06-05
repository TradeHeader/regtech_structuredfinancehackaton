package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.validation.PayoutBaseTypeFormatValidator;
import cdm.product.common.settlement.validation.PayoutBaseValidator;
import cdm.product.common.settlement.validation.exists.PayoutBaseOnlyExistsValidator;
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
@RosettaMeta(model=PayoutBase.class)
public class PayoutBaseMeta implements RosettaMetaData<PayoutBase> {

	@Override
	public List<Validator<? super PayoutBase>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PayoutBase, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PayoutBase> validator() {
		return new PayoutBaseValidator();
	}

	@Override
	public Validator<? super PayoutBase> typeFormatValidator() {
		return new PayoutBaseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PayoutBase, Set<String>> onlyExistsValidator() {
		return new PayoutBaseOnlyExistsValidator();
	}
}
