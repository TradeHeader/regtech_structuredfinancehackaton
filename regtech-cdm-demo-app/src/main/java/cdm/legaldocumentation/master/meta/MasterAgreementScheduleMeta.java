package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.MasterAgreementSchedule;
import cdm.legaldocumentation.master.validation.MasterAgreementScheduleTypeFormatValidator;
import cdm.legaldocumentation.master.validation.MasterAgreementScheduleValidator;
import cdm.legaldocumentation.master.validation.exists.MasterAgreementScheduleOnlyExistsValidator;
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
@RosettaMeta(model=MasterAgreementSchedule.class)
public class MasterAgreementScheduleMeta implements RosettaMetaData<MasterAgreementSchedule> {

	@Override
	public List<Validator<? super MasterAgreementSchedule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super MasterAgreementSchedule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MasterAgreementSchedule> validator() {
		return new MasterAgreementScheduleValidator();
	}

	@Override
	public Validator<? super MasterAgreementSchedule> typeFormatValidator() {
		return new MasterAgreementScheduleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MasterAgreementSchedule, Set<String>> onlyExistsValidator() {
		return new MasterAgreementScheduleOnlyExistsValidator();
	}
}
