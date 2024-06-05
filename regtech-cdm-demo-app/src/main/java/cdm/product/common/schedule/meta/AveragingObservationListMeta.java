package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.AveragingObservationList;
import cdm.product.common.schedule.validation.AveragingObservationListTypeFormatValidator;
import cdm.product.common.schedule.validation.AveragingObservationListValidator;
import cdm.product.common.schedule.validation.exists.AveragingObservationListOnlyExistsValidator;
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
@RosettaMeta(model=AveragingObservationList.class)
public class AveragingObservationListMeta implements RosettaMetaData<AveragingObservationList> {

	@Override
	public List<Validator<? super AveragingObservationList>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AveragingObservationList, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AveragingObservationList> validator() {
		return new AveragingObservationListValidator();
	}

	@Override
	public Validator<? super AveragingObservationList> typeFormatValidator() {
		return new AveragingObservationListTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AveragingObservationList, Set<String>> onlyExistsValidator() {
		return new AveragingObservationListOnlyExistsValidator();
	}
}
