package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.PhysicalSettlementPeriod;
import cdm.product.common.settlement.validation.PhysicalSettlementPeriodTypeFormatValidator;
import cdm.product.common.settlement.validation.PhysicalSettlementPeriodValidator;
import cdm.product.common.settlement.validation.exists.PhysicalSettlementPeriodOnlyExistsValidator;
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
@RosettaMeta(model=PhysicalSettlementPeriod.class)
public class PhysicalSettlementPeriodMeta implements RosettaMetaData<PhysicalSettlementPeriod> {

	@Override
	public List<Validator<? super PhysicalSettlementPeriod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.settlement.validation.datarule.PhysicalSettlementPeriodOneOf0.class),
			factory.create(cdm.product.common.settlement.validation.datarule.PhysicalSettlementPeriodBusinessDays.class),
			factory.create(cdm.product.common.settlement.validation.datarule.PhysicalSettlementPeriodMaximumBusinessDays.class)
		);
	}
	
	@Override
	public List<Function<? super PhysicalSettlementPeriod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PhysicalSettlementPeriod> validator() {
		return new PhysicalSettlementPeriodValidator();
	}

	@Override
	public Validator<? super PhysicalSettlementPeriod> typeFormatValidator() {
		return new PhysicalSettlementPeriodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PhysicalSettlementPeriod, Set<String>> onlyExistsValidator() {
		return new PhysicalSettlementPeriodOnlyExistsValidator();
	}
}
