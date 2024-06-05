package cdm.legaldocumentation.csa.meta;

import cdm.legaldocumentation.csa.CollateralTransferAgreementElections;
import cdm.legaldocumentation.csa.validation.CollateralTransferAgreementElectionsTypeFormatValidator;
import cdm.legaldocumentation.csa.validation.CollateralTransferAgreementElectionsValidator;
import cdm.legaldocumentation.csa.validation.exists.CollateralTransferAgreementElectionsOnlyExistsValidator;
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
@RosettaMeta(model=CollateralTransferAgreementElections.class)
public class CollateralTransferAgreementElectionsMeta implements RosettaMetaData<CollateralTransferAgreementElections> {

	@Override
	public List<Validator<? super CollateralTransferAgreementElections>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CollateralTransferAgreementElections, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralTransferAgreementElections> validator() {
		return new CollateralTransferAgreementElectionsValidator();
	}

	@Override
	public Validator<? super CollateralTransferAgreementElections> typeFormatValidator() {
		return new CollateralTransferAgreementElectionsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralTransferAgreementElections, Set<String>> onlyExistsValidator() {
		return new CollateralTransferAgreementElectionsOnlyExistsValidator();
	}
}
