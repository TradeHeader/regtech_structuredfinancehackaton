package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.PaymentDetail;
import cdm.product.common.settlement.validation.PaymentDetailTypeFormatValidator;
import cdm.product.common.settlement.validation.PaymentDetailValidator;
import cdm.product.common.settlement.validation.exists.PaymentDetailOnlyExistsValidator;
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
@RosettaMeta(model=PaymentDetail.class)
public class PaymentDetailMeta implements RosettaMetaData<PaymentDetail> {

	@Override
	public List<Validator<? super PaymentDetail>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PaymentDetail, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PaymentDetail> validator() {
		return new PaymentDetailValidator();
	}

	@Override
	public Validator<? super PaymentDetail> typeFormatValidator() {
		return new PaymentDetailTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PaymentDetail, Set<String>> onlyExistsValidator() {
		return new PaymentDetailOnlyExistsValidator();
	}
}
