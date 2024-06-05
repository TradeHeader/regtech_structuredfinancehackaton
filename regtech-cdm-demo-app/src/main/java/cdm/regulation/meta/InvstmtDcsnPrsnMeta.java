package cdm.regulation.meta;

import cdm.regulation.InvstmtDcsnPrsn;
import cdm.regulation.validation.InvstmtDcsnPrsnTypeFormatValidator;
import cdm.regulation.validation.InvstmtDcsnPrsnValidator;
import cdm.regulation.validation.exists.InvstmtDcsnPrsnOnlyExistsValidator;
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
@RosettaMeta(model=InvstmtDcsnPrsn.class)
public class InvstmtDcsnPrsnMeta implements RosettaMetaData<InvstmtDcsnPrsn> {

	@Override
	public List<Validator<? super InvstmtDcsnPrsn>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super InvstmtDcsnPrsn, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super InvstmtDcsnPrsn> validator() {
		return new InvstmtDcsnPrsnValidator();
	}

	@Override
	public Validator<? super InvstmtDcsnPrsn> typeFormatValidator() {
		return new InvstmtDcsnPrsnTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super InvstmtDcsnPrsn, Set<String>> onlyExistsValidator() {
		return new InvstmtDcsnPrsnOnlyExistsValidator();
	}
}
