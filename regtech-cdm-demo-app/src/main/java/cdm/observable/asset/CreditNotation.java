package cdm.observable.asset;

import cdm.observable.asset.CreditNotation;
import cdm.observable.asset.CreditNotation.CreditNotationBuilder;
import cdm.observable.asset.CreditNotation.CreditNotationBuilderImpl;
import cdm.observable.asset.CreditNotation.CreditNotationImpl;
import cdm.observable.asset.CreditRatingAgencyEnum;
import cdm.observable.asset.CreditRatingCreditWatchEnum;
import cdm.observable.asset.CreditRatingDebt;
import cdm.observable.asset.CreditRatingOutlookEnum;
import cdm.observable.asset.meta.CreditNotationMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Represents a class to specify the credit notation as the combination of agency, notation, scale and debt type qualifications.
 * @version ${project.version}
 */
@RosettaDataType(value="CreditNotation", builder=CreditNotation.CreditNotationBuilderImpl.class, version="${project.version}")
public interface CreditNotation extends RosettaModelObject {

	CreditNotationMeta metaData = new CreditNotationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies The credit agency to which the other variables (notation, scale, debt type) refer to.
	 */
	CreditRatingAgencyEnum getAgency();
	/**
	 * Specifies The credit rating notation. As it varies among credit rating agencies, FpML doesn&#39;t specify a default scheme.
	 */
	FieldWithMetaString getNotation();
	/**
	 * Specifies the credit rating scale, with a typical distinction between short term, long term. FpML doesn&#39;t specify a default scheme, which is hence not specified as an enumeration as part of the CDM.
	 */
	FieldWithMetaString getScale();
	/**
	 * Specifies the credit rating debt type (e.g. long term, high yield, deposits, ...) associated with the credit rating notation and scale.
	 */
	CreditRatingDebt getDebt();
	/**
	 * Assesses the potential direction of a long-term credit rating over the intermediate term, which is generally up to two years for investment grade and generally up to one year for speculative grade.
	 */
	CreditRatingOutlookEnum getOutlook();
	/**
	 * Indicates the potential direction of a short-term or long-term rating. It focuses on identifiable events and short-term trends that cause ratings to be placed under special surveillance.
	 */
	CreditRatingCreditWatchEnum getCreditWatch();

	/*********************** Build Methods  ***********************/
	CreditNotation build();
	
	CreditNotation.CreditNotationBuilder toBuilder();
	
	static CreditNotation.CreditNotationBuilder builder() {
		return new CreditNotation.CreditNotationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CreditNotation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CreditNotation> getType() {
		return CreditNotation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("agency"), CreditRatingAgencyEnum.class, getAgency(), this);
		processRosetta(path.newSubPath("notation"), processor, FieldWithMetaString.class, getNotation());
		processRosetta(path.newSubPath("scale"), processor, FieldWithMetaString.class, getScale());
		processRosetta(path.newSubPath("debt"), processor, CreditRatingDebt.class, getDebt());
		processor.processBasic(path.newSubPath("outlook"), CreditRatingOutlookEnum.class, getOutlook(), this);
		processor.processBasic(path.newSubPath("creditWatch"), CreditRatingCreditWatchEnum.class, getCreditWatch(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CreditNotationBuilder extends CreditNotation, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateNotation();
		FieldWithMetaString.FieldWithMetaStringBuilder getNotation();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateScale();
		FieldWithMetaString.FieldWithMetaStringBuilder getScale();
		CreditRatingDebt.CreditRatingDebtBuilder getOrCreateDebt();
		CreditRatingDebt.CreditRatingDebtBuilder getDebt();
		CreditNotation.CreditNotationBuilder setAgency(CreditRatingAgencyEnum agency);
		CreditNotation.CreditNotationBuilder setNotation(FieldWithMetaString notation0);
		CreditNotation.CreditNotationBuilder setNotationValue(String notation1);
		CreditNotation.CreditNotationBuilder setScale(FieldWithMetaString scale0);
		CreditNotation.CreditNotationBuilder setScaleValue(String scale1);
		CreditNotation.CreditNotationBuilder setDebt(CreditRatingDebt debt);
		CreditNotation.CreditNotationBuilder setOutlook(CreditRatingOutlookEnum outlook);
		CreditNotation.CreditNotationBuilder setCreditWatch(CreditRatingCreditWatchEnum creditWatch);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("agency"), CreditRatingAgencyEnum.class, getAgency(), this);
			processRosetta(path.newSubPath("notation"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getNotation());
			processRosetta(path.newSubPath("scale"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getScale());
			processRosetta(path.newSubPath("debt"), processor, CreditRatingDebt.CreditRatingDebtBuilder.class, getDebt());
			processor.processBasic(path.newSubPath("outlook"), CreditRatingOutlookEnum.class, getOutlook(), this);
			processor.processBasic(path.newSubPath("creditWatch"), CreditRatingCreditWatchEnum.class, getCreditWatch(), this);
		}
		

		CreditNotation.CreditNotationBuilder prune();
	}

	/*********************** Immutable Implementation of CreditNotation  ***********************/
	class CreditNotationImpl implements CreditNotation {
		private final CreditRatingAgencyEnum agency;
		private final FieldWithMetaString notation;
		private final FieldWithMetaString scale;
		private final CreditRatingDebt debt;
		private final CreditRatingOutlookEnum outlook;
		private final CreditRatingCreditWatchEnum creditWatch;
		
		protected CreditNotationImpl(CreditNotation.CreditNotationBuilder builder) {
			this.agency = builder.getAgency();
			this.notation = ofNullable(builder.getNotation()).map(f->f.build()).orElse(null);
			this.scale = ofNullable(builder.getScale()).map(f->f.build()).orElse(null);
			this.debt = ofNullable(builder.getDebt()).map(f->f.build()).orElse(null);
			this.outlook = builder.getOutlook();
			this.creditWatch = builder.getCreditWatch();
		}
		
		@Override
		@RosettaAttribute("agency")
		public CreditRatingAgencyEnum getAgency() {
			return agency;
		}
		
		@Override
		@RosettaAttribute("notation")
		public FieldWithMetaString getNotation() {
			return notation;
		}
		
		@Override
		@RosettaAttribute("scale")
		public FieldWithMetaString getScale() {
			return scale;
		}
		
		@Override
		@RosettaAttribute("debt")
		public CreditRatingDebt getDebt() {
			return debt;
		}
		
		@Override
		@RosettaAttribute("outlook")
		public CreditRatingOutlookEnum getOutlook() {
			return outlook;
		}
		
		@Override
		@RosettaAttribute("creditWatch")
		public CreditRatingCreditWatchEnum getCreditWatch() {
			return creditWatch;
		}
		
		@Override
		public CreditNotation build() {
			return this;
		}
		
		@Override
		public CreditNotation.CreditNotationBuilder toBuilder() {
			CreditNotation.CreditNotationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CreditNotation.CreditNotationBuilder builder) {
			ofNullable(getAgency()).ifPresent(builder::setAgency);
			ofNullable(getNotation()).ifPresent(builder::setNotation);
			ofNullable(getScale()).ifPresent(builder::setScale);
			ofNullable(getDebt()).ifPresent(builder::setDebt);
			ofNullable(getOutlook()).ifPresent(builder::setOutlook);
			ofNullable(getCreditWatch()).ifPresent(builder::setCreditWatch);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CreditNotation _that = getType().cast(o);
		
			if (!Objects.equals(agency, _that.getAgency())) return false;
			if (!Objects.equals(notation, _that.getNotation())) return false;
			if (!Objects.equals(scale, _that.getScale())) return false;
			if (!Objects.equals(debt, _that.getDebt())) return false;
			if (!Objects.equals(outlook, _that.getOutlook())) return false;
			if (!Objects.equals(creditWatch, _that.getCreditWatch())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (agency != null ? agency.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (notation != null ? notation.hashCode() : 0);
			_result = 31 * _result + (scale != null ? scale.hashCode() : 0);
			_result = 31 * _result + (debt != null ? debt.hashCode() : 0);
			_result = 31 * _result + (outlook != null ? outlook.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (creditWatch != null ? creditWatch.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditNotation {" +
				"agency=" + this.agency + ", " +
				"notation=" + this.notation + ", " +
				"scale=" + this.scale + ", " +
				"debt=" + this.debt + ", " +
				"outlook=" + this.outlook + ", " +
				"creditWatch=" + this.creditWatch +
			'}';
		}
	}

	/*********************** Builder Implementation of CreditNotation  ***********************/
	class CreditNotationBuilderImpl implements CreditNotation.CreditNotationBuilder {
	
		protected CreditRatingAgencyEnum agency;
		protected FieldWithMetaString.FieldWithMetaStringBuilder notation;
		protected FieldWithMetaString.FieldWithMetaStringBuilder scale;
		protected CreditRatingDebt.CreditRatingDebtBuilder debt;
		protected CreditRatingOutlookEnum outlook;
		protected CreditRatingCreditWatchEnum creditWatch;
	
		public CreditNotationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("agency")
		public CreditRatingAgencyEnum getAgency() {
			return agency;
		}
		
		@Override
		@RosettaAttribute("notation")
		public FieldWithMetaString.FieldWithMetaStringBuilder getNotation() {
			return notation;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateNotation() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (notation!=null) {
				result = notation;
			}
			else {
				result = notation = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("scale")
		public FieldWithMetaString.FieldWithMetaStringBuilder getScale() {
			return scale;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateScale() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (scale!=null) {
				result = scale;
			}
			else {
				result = scale = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("debt")
		public CreditRatingDebt.CreditRatingDebtBuilder getDebt() {
			return debt;
		}
		
		@Override
		public CreditRatingDebt.CreditRatingDebtBuilder getOrCreateDebt() {
			CreditRatingDebt.CreditRatingDebtBuilder result;
			if (debt!=null) {
				result = debt;
			}
			else {
				result = debt = CreditRatingDebt.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("outlook")
		public CreditRatingOutlookEnum getOutlook() {
			return outlook;
		}
		
		@Override
		@RosettaAttribute("creditWatch")
		public CreditRatingCreditWatchEnum getCreditWatch() {
			return creditWatch;
		}
		
	
		@Override
		@RosettaAttribute("agency")
		public CreditNotation.CreditNotationBuilder setAgency(CreditRatingAgencyEnum agency) {
			this.agency = agency==null?null:agency;
			return this;
		}
		@Override
		@RosettaAttribute("notation")
		public CreditNotation.CreditNotationBuilder setNotation(FieldWithMetaString notation) {
			this.notation = notation==null?null:notation.toBuilder();
			return this;
		}
		@Override
		public CreditNotation.CreditNotationBuilder setNotationValue(String notation) {
			this.getOrCreateNotation().setValue(notation);
			return this;
		}
		@Override
		@RosettaAttribute("scale")
		public CreditNotation.CreditNotationBuilder setScale(FieldWithMetaString scale) {
			this.scale = scale==null?null:scale.toBuilder();
			return this;
		}
		@Override
		public CreditNotation.CreditNotationBuilder setScaleValue(String scale) {
			this.getOrCreateScale().setValue(scale);
			return this;
		}
		@Override
		@RosettaAttribute("debt")
		public CreditNotation.CreditNotationBuilder setDebt(CreditRatingDebt debt) {
			this.debt = debt==null?null:debt.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("outlook")
		public CreditNotation.CreditNotationBuilder setOutlook(CreditRatingOutlookEnum outlook) {
			this.outlook = outlook==null?null:outlook;
			return this;
		}
		@Override
		@RosettaAttribute("creditWatch")
		public CreditNotation.CreditNotationBuilder setCreditWatch(CreditRatingCreditWatchEnum creditWatch) {
			this.creditWatch = creditWatch==null?null:creditWatch;
			return this;
		}
		
		@Override
		public CreditNotation build() {
			return new CreditNotation.CreditNotationImpl(this);
		}
		
		@Override
		public CreditNotation.CreditNotationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditNotation.CreditNotationBuilder prune() {
			if (notation!=null && !notation.prune().hasData()) notation = null;
			if (scale!=null && !scale.prune().hasData()) scale = null;
			if (debt!=null && !debt.prune().hasData()) debt = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAgency()!=null) return true;
			if (getNotation()!=null) return true;
			if (getScale()!=null) return true;
			if (getDebt()!=null && getDebt().hasData()) return true;
			if (getOutlook()!=null) return true;
			if (getCreditWatch()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditNotation.CreditNotationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CreditNotation.CreditNotationBuilder o = (CreditNotation.CreditNotationBuilder) other;
			
			merger.mergeRosetta(getNotation(), o.getNotation(), this::setNotation);
			merger.mergeRosetta(getScale(), o.getScale(), this::setScale);
			merger.mergeRosetta(getDebt(), o.getDebt(), this::setDebt);
			
			merger.mergeBasic(getAgency(), o.getAgency(), this::setAgency);
			merger.mergeBasic(getOutlook(), o.getOutlook(), this::setOutlook);
			merger.mergeBasic(getCreditWatch(), o.getCreditWatch(), this::setCreditWatch);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CreditNotation _that = getType().cast(o);
		
			if (!Objects.equals(agency, _that.getAgency())) return false;
			if (!Objects.equals(notation, _that.getNotation())) return false;
			if (!Objects.equals(scale, _that.getScale())) return false;
			if (!Objects.equals(debt, _that.getDebt())) return false;
			if (!Objects.equals(outlook, _that.getOutlook())) return false;
			if (!Objects.equals(creditWatch, _that.getCreditWatch())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (agency != null ? agency.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (notation != null ? notation.hashCode() : 0);
			_result = 31 * _result + (scale != null ? scale.hashCode() : 0);
			_result = 31 * _result + (debt != null ? debt.hashCode() : 0);
			_result = 31 * _result + (outlook != null ? outlook.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (creditWatch != null ? creditWatch.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditNotationBuilder {" +
				"agency=" + this.agency + ", " +
				"notation=" + this.notation + ", " +
				"scale=" + this.scale + ", " +
				"debt=" + this.debt + ", " +
				"outlook=" + this.outlook + ", " +
				"creditWatch=" + this.creditWatch +
			'}';
		}
	}
}
