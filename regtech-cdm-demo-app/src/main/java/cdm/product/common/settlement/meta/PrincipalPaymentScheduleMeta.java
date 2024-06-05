package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.PrincipalPaymentSchedule;
import cdm.product.common.settlement.validation.PrincipalPaymentScheduleTypeFormatValidator;
import cdm.product.common.settlement.validation.PrincipalPaymentScheduleValidator;
import cdm.product.common.settlement.validation.exists.PrincipalPaymentScheduleOnlyExistsValidator;
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
@RosettaMeta(model=PrincipalPaymentSchedule.class)
public class PrincipalPaymentScheduleMeta implements RosettaMetaData<PrincipalPaymentSchedule> {

	@Override
	public List<Validator<? super PrincipalPaymentSchedule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PrincipalPaymentSchedule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PrincipalPaymentSchedule> validator() {
		return new PrincipalPaymentScheduleValidator();
	}

	@Override
	public Validator<? super PrincipalPaymentSchedule> typeFormatValidator() {
		return new PrincipalPaymentScheduleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PrincipalPaymentSchedule, Set<String>> onlyExistsValidator() {
		return new PrincipalPaymentScheduleOnlyExistsValidator();
	}
}
