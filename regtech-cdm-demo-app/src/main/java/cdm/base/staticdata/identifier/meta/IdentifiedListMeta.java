package cdm.base.staticdata.identifier.meta;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.base.staticdata.identifier.validation.IdentifiedListTypeFormatValidator;
import cdm.base.staticdata.identifier.validation.IdentifiedListValidator;
import cdm.base.staticdata.identifier.validation.exists.IdentifiedListOnlyExistsValidator;
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
@RosettaMeta(model=IdentifiedList.class)
public class IdentifiedListMeta implements RosettaMetaData<IdentifiedList> {

	@Override
	public List<Validator<? super IdentifiedList>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super IdentifiedList, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super IdentifiedList> validator() {
		return new IdentifiedListValidator();
	}

	@Override
	public Validator<? super IdentifiedList> typeFormatValidator() {
		return new IdentifiedListTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super IdentifiedList, Set<String>> onlyExistsValidator() {
		return new IdentifiedListOnlyExistsValidator();
	}
}
