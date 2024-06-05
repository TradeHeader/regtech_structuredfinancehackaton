package cdm.base.datetime.meta;

import cdm.base.datetime.AdjustedRelativeDateOffset;
import cdm.base.datetime.validation.AdjustedRelativeDateOffsetTypeFormatValidator;
import cdm.base.datetime.validation.AdjustedRelativeDateOffsetValidator;
import cdm.base.datetime.validation.exists.AdjustedRelativeDateOffsetOnlyExistsValidator;
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
@RosettaMeta(model=AdjustedRelativeDateOffset.class)
public class AdjustedRelativeDateOffsetMeta implements RosettaMetaData<AdjustedRelativeDateOffset> {

	@Override
	public List<Validator<? super AdjustedRelativeDateOffset>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.datetime.validation.datarule.OffsetDayType.class),
			factory.create(cdm.base.datetime.validation.datarule.PeriodDayPeriod.class)
		);
	}
	
	@Override
	public List<Function<? super AdjustedRelativeDateOffset, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AdjustedRelativeDateOffset> validator() {
		return new AdjustedRelativeDateOffsetValidator();
	}

	@Override
	public Validator<? super AdjustedRelativeDateOffset> typeFormatValidator() {
		return new AdjustedRelativeDateOffsetTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AdjustedRelativeDateOffset, Set<String>> onlyExistsValidator() {
		return new AdjustedRelativeDateOffsetOnlyExistsValidator();
	}
}
