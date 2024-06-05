package cdm.observable.event.meta;

import cdm.observable.event.CreditEventNotice;
import cdm.observable.event.validation.CreditEventNoticeTypeFormatValidator;
import cdm.observable.event.validation.CreditEventNoticeValidator;
import cdm.observable.event.validation.exists.CreditEventNoticeOnlyExistsValidator;
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
@RosettaMeta(model=CreditEventNotice.class)
public class CreditEventNoticeMeta implements RosettaMetaData<CreditEventNotice> {

	@Override
	public List<Validator<? super CreditEventNotice>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CreditEventNotice, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CreditEventNotice> validator() {
		return new CreditEventNoticeValidator();
	}

	@Override
	public Validator<? super CreditEventNotice> typeFormatValidator() {
		return new CreditEventNoticeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CreditEventNotice, Set<String>> onlyExistsValidator() {
		return new CreditEventNoticeOnlyExistsValidator();
	}
}
