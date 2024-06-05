package cdm.event.common.meta;

import cdm.event.common.TradePricingReport;
import cdm.event.common.validation.TradePricingReportTypeFormatValidator;
import cdm.event.common.validation.TradePricingReportValidator;
import cdm.event.common.validation.exists.TradePricingReportOnlyExistsValidator;
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
@RosettaMeta(model=TradePricingReport.class)
public class TradePricingReportMeta implements RosettaMetaData<TradePricingReport> {

	@Override
	public List<Validator<? super TradePricingReport>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TradePricingReport, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TradePricingReport> validator() {
		return new TradePricingReportValidator();
	}

	@Override
	public Validator<? super TradePricingReport> typeFormatValidator() {
		return new TradePricingReportTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TradePricingReport, Set<String>> onlyExistsValidator() {
		return new TradePricingReportOnlyExistsValidator();
	}
}
