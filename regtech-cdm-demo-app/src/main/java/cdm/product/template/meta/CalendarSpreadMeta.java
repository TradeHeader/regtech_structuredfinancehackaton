package cdm.product.template.meta;

import cdm.product.template.CalendarSpread;
import cdm.product.template.validation.CalendarSpreadTypeFormatValidator;
import cdm.product.template.validation.CalendarSpreadValidator;
import cdm.product.template.validation.exists.CalendarSpreadOnlyExistsValidator;
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
@RosettaMeta(model=CalendarSpread.class)
public class CalendarSpreadMeta implements RosettaMetaData<CalendarSpread> {

	@Override
	public List<Validator<? super CalendarSpread>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CalendarSpread, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalendarSpread> validator() {
		return new CalendarSpreadValidator();
	}

	@Override
	public Validator<? super CalendarSpread> typeFormatValidator() {
		return new CalendarSpreadTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalendarSpread, Set<String>> onlyExistsValidator() {
		return new CalendarSpreadOnlyExistsValidator();
	}
}
