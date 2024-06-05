package cdm.product.asset.meta;

import cdm.product.asset.CreditIndexReferenceInformation;
import cdm.product.asset.validation.CreditIndexReferenceInformationTypeFormatValidator;
import cdm.product.asset.validation.CreditIndexReferenceInformationValidator;
import cdm.product.asset.validation.exists.CreditIndexReferenceInformationOnlyExistsValidator;
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
@RosettaMeta(model=CreditIndexReferenceInformation.class)
public class CreditIndexReferenceInformationMeta implements RosettaMetaData<CreditIndexReferenceInformation> {

	@Override
	public List<Validator<? super CreditIndexReferenceInformation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.CreditIndexReferenceInformationIndexSeries.class),
			factory.create(cdm.product.asset.validation.datarule.CreditIndexReferenceInformationIndexAnnexVersion.class),
			factory.create(cdm.product.asset.validation.datarule.CreditIndexReferenceInformationIndexFactor.class),
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.IndexReferenceInformationIndexAttributes.class)
		);
	}
	
	@Override
	public List<Function<? super CreditIndexReferenceInformation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CreditIndexReferenceInformation> validator() {
		return new CreditIndexReferenceInformationValidator();
	}

	@Override
	public Validator<? super CreditIndexReferenceInformation> typeFormatValidator() {
		return new CreditIndexReferenceInformationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CreditIndexReferenceInformation, Set<String>> onlyExistsValidator() {
		return new CreditIndexReferenceInformationOnlyExistsValidator();
	}
}
