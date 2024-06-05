package cdm.event.common.meta;

import cdm.event.common.BillingSummary;
import cdm.event.common.validation.BillingSummaryTypeFormatValidator;
import cdm.event.common.validation.BillingSummaryValidator;
import cdm.event.common.validation.exists.BillingSummaryOnlyExistsValidator;
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
@RosettaMeta(model=BillingSummary.class)
public class BillingSummaryMeta implements RosettaMetaData<BillingSummary> {

	@Override
	public List<Validator<? super BillingSummary>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.BillingSummaryGrandTotal.class),
			factory.create(cdm.event.common.validation.datarule.BillingSummaryParentTotal.class),
			factory.create(cdm.event.common.validation.datarule.BillingSummaryAccountTotal.class)
		);
	}
	
	@Override
	public List<Function<? super BillingSummary, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BillingSummary> validator() {
		return new BillingSummaryValidator();
	}

	@Override
	public Validator<? super BillingSummary> typeFormatValidator() {
		return new BillingSummaryTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BillingSummary, Set<String>> onlyExistsValidator() {
		return new BillingSummaryOnlyExistsValidator();
	}
}
