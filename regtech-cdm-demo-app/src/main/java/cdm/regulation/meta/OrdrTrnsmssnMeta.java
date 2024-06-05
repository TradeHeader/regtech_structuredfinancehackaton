package cdm.regulation.meta;

import cdm.regulation.OrdrTrnsmssn;
import cdm.regulation.validation.OrdrTrnsmssnTypeFormatValidator;
import cdm.regulation.validation.OrdrTrnsmssnValidator;
import cdm.regulation.validation.exists.OrdrTrnsmssnOnlyExistsValidator;
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
@RosettaMeta(model=OrdrTrnsmssn.class)
public class OrdrTrnsmssnMeta implements RosettaMetaData<OrdrTrnsmssn> {

	@Override
	public List<Validator<? super OrdrTrnsmssn>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super OrdrTrnsmssn, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super OrdrTrnsmssn> validator() {
		return new OrdrTrnsmssnValidator();
	}

	@Override
	public Validator<? super OrdrTrnsmssn> typeFormatValidator() {
		return new OrdrTrnsmssnTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super OrdrTrnsmssn, Set<String>> onlyExistsValidator() {
		return new OrdrTrnsmssnOnlyExistsValidator();
	}
}
