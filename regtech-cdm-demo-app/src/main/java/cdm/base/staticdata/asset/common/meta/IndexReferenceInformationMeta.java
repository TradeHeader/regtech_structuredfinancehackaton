package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.IndexReferenceInformation;
import cdm.base.staticdata.asset.common.validation.IndexReferenceInformationTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.IndexReferenceInformationValidator;
import cdm.base.staticdata.asset.common.validation.exists.IndexReferenceInformationOnlyExistsValidator;
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
@RosettaMeta(model=IndexReferenceInformation.class)
public class IndexReferenceInformationMeta implements RosettaMetaData<IndexReferenceInformation> {

	@Override
	public List<Validator<? super IndexReferenceInformation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.IndexReferenceInformationIndexAttributes.class)
		);
	}
	
	@Override
	public List<Function<? super IndexReferenceInformation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super IndexReferenceInformation> validator() {
		return new IndexReferenceInformationValidator();
	}

	@Override
	public Validator<? super IndexReferenceInformation> typeFormatValidator() {
		return new IndexReferenceInformationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super IndexReferenceInformation, Set<String>> onlyExistsValidator() {
		return new IndexReferenceInformationOnlyExistsValidator();
	}
}
