package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.FxFixingDate;
import cdm.product.common.settlement.validation.FxFixingDateTypeFormatValidator;
import cdm.product.common.settlement.validation.FxFixingDateValidator;
import cdm.product.common.settlement.validation.exists.FxFixingDateOnlyExistsValidator;
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
@RosettaMeta(model=FxFixingDate.class)
public class FxFixingDateMeta implements RosettaMetaData<FxFixingDate> {

	@Override
	public List<Validator<? super FxFixingDate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.common.settlement.validation.datarule.FxFixingDateBusinessCentersChoice.class),
			factory.create(cdm.product.common.settlement.validation.datarule.FxFixingDateDateChoice.class),
			factory.create(cdm.base.datetime.validation.datarule.OffsetDayType.class),
			factory.create(cdm.base.datetime.validation.datarule.PeriodDayPeriod.class)
		);
	}
	
	@Override
	public List<Function<? super FxFixingDate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FxFixingDate> validator() {
		return new FxFixingDateValidator();
	}

	@Override
	public Validator<? super FxFixingDate> typeFormatValidator() {
		return new FxFixingDateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FxFixingDate, Set<String>> onlyExistsValidator() {
		return new FxFixingDateOnlyExistsValidator();
	}
}
