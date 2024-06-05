package cdm.regulation.meta;

import cdm.regulation.Document;
import cdm.regulation.validation.DocumentTypeFormatValidator;
import cdm.regulation.validation.DocumentValidator;
import cdm.regulation.validation.exists.DocumentOnlyExistsValidator;
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
@RosettaMeta(model=Document.class)
public class DocumentMeta implements RosettaMetaData<Document> {

	@Override
	public List<Validator<? super Document>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Document, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Document> validator() {
		return new DocumentValidator();
	}

	@Override
	public Validator<? super Document> typeFormatValidator() {
		return new DocumentTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Document, Set<String>> onlyExistsValidator() {
		return new DocumentOnlyExistsValidator();
	}
}
