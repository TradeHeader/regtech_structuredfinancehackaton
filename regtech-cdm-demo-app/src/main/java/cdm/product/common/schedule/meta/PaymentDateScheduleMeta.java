package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.PaymentDateSchedule;
import cdm.product.common.schedule.validation.PaymentDateScheduleTypeFormatValidator;
import cdm.product.common.schedule.validation.PaymentDateScheduleValidator;
import cdm.product.common.schedule.validation.exists.PaymentDateScheduleOnlyExistsValidator;
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
@RosettaMeta(model=PaymentDateSchedule.class)
public class PaymentDateScheduleMeta implements RosettaMetaData<PaymentDateSchedule> {

	@Override
	public List<Validator<? super PaymentDateSchedule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PaymentDateSchedule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PaymentDateSchedule> validator() {
		return new PaymentDateScheduleValidator();
	}

	@Override
	public Validator<? super PaymentDateSchedule> typeFormatValidator() {
		return new PaymentDateScheduleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PaymentDateSchedule, Set<String>> onlyExistsValidator() {
		return new PaymentDateScheduleOnlyExistsValidator();
	}
}
