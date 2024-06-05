package cdm.legaldocumentation.master;

import cdm.legaldocumentation.master.EquityAdditionalTerms;
import cdm.legaldocumentation.master.FxAdditionalTerms;
import cdm.legaldocumentation.master.TransactionAdditionalTerms;
import cdm.legaldocumentation.master.TransactionAdditionalTerms.TransactionAdditionalTermsBuilder;
import cdm.legaldocumentation.master.TransactionAdditionalTerms.TransactionAdditionalTermsBuilderImpl;
import cdm.legaldocumentation.master.TransactionAdditionalTerms.TransactionAdditionalTermsImpl;
import cdm.legaldocumentation.master.meta.TransactionAdditionalTermsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Additional specification for the extraordinary events that may affect a trade and the related contractual rights and obligation of the parties when this happens. Such terms are typically required to extend the economics terms, for the purpose of producing the final legal contractual form of the Transaction.
 * @version ${project.version}
 */
@RosettaDataType(value="TransactionAdditionalTerms", builder=TransactionAdditionalTerms.TransactionAdditionalTermsBuilderImpl.class, version="${project.version}")
public interface TransactionAdditionalTerms extends RosettaModelObject {

	TransactionAdditionalTermsMeta metaData = new TransactionAdditionalTermsMeta();

	/*********************** Getter Methods  ***********************/
	EquityAdditionalTerms getEquityAdditionalTerms();
	FxAdditionalTerms getForeignExchangeAdditionalTerms();
	String getCommoditiesAdditionalTerms();
	String getCreditAdditionalTerms();
	String getInterestRateAdditionalTerms();
	String getDigitalAssetAdditionalTerms();

	/*********************** Build Methods  ***********************/
	TransactionAdditionalTerms build();
	
	TransactionAdditionalTerms.TransactionAdditionalTermsBuilder toBuilder();
	
	static TransactionAdditionalTerms.TransactionAdditionalTermsBuilder builder() {
		return new TransactionAdditionalTerms.TransactionAdditionalTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TransactionAdditionalTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TransactionAdditionalTerms> getType() {
		return TransactionAdditionalTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("equityAdditionalTerms"), processor, EquityAdditionalTerms.class, getEquityAdditionalTerms());
		processRosetta(path.newSubPath("foreignExchangeAdditionalTerms"), processor, FxAdditionalTerms.class, getForeignExchangeAdditionalTerms());
		processor.processBasic(path.newSubPath("commoditiesAdditionalTerms"), String.class, getCommoditiesAdditionalTerms(), this);
		processor.processBasic(path.newSubPath("creditAdditionalTerms"), String.class, getCreditAdditionalTerms(), this);
		processor.processBasic(path.newSubPath("interestRateAdditionalTerms"), String.class, getInterestRateAdditionalTerms(), this);
		processor.processBasic(path.newSubPath("digitalAssetAdditionalTerms"), String.class, getDigitalAssetAdditionalTerms(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface TransactionAdditionalTermsBuilder extends TransactionAdditionalTerms, RosettaModelObjectBuilder {
		EquityAdditionalTerms.EquityAdditionalTermsBuilder getOrCreateEquityAdditionalTerms();
		EquityAdditionalTerms.EquityAdditionalTermsBuilder getEquityAdditionalTerms();
		FxAdditionalTerms.FxAdditionalTermsBuilder getOrCreateForeignExchangeAdditionalTerms();
		FxAdditionalTerms.FxAdditionalTermsBuilder getForeignExchangeAdditionalTerms();
		TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setEquityAdditionalTerms(EquityAdditionalTerms equityAdditionalTerms);
		TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setForeignExchangeAdditionalTerms(FxAdditionalTerms foreignExchangeAdditionalTerms);
		TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setCommoditiesAdditionalTerms(String commoditiesAdditionalTerms);
		TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setCreditAdditionalTerms(String creditAdditionalTerms);
		TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setInterestRateAdditionalTerms(String interestRateAdditionalTerms);
		TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setDigitalAssetAdditionalTerms(String digitalAssetAdditionalTerms);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("equityAdditionalTerms"), processor, EquityAdditionalTerms.EquityAdditionalTermsBuilder.class, getEquityAdditionalTerms());
			processRosetta(path.newSubPath("foreignExchangeAdditionalTerms"), processor, FxAdditionalTerms.FxAdditionalTermsBuilder.class, getForeignExchangeAdditionalTerms());
			processor.processBasic(path.newSubPath("commoditiesAdditionalTerms"), String.class, getCommoditiesAdditionalTerms(), this);
			processor.processBasic(path.newSubPath("creditAdditionalTerms"), String.class, getCreditAdditionalTerms(), this);
			processor.processBasic(path.newSubPath("interestRateAdditionalTerms"), String.class, getInterestRateAdditionalTerms(), this);
			processor.processBasic(path.newSubPath("digitalAssetAdditionalTerms"), String.class, getDigitalAssetAdditionalTerms(), this);
		}
		

		TransactionAdditionalTerms.TransactionAdditionalTermsBuilder prune();
	}

	/*********************** Immutable Implementation of TransactionAdditionalTerms  ***********************/
	class TransactionAdditionalTermsImpl implements TransactionAdditionalTerms {
		private final EquityAdditionalTerms equityAdditionalTerms;
		private final FxAdditionalTerms foreignExchangeAdditionalTerms;
		private final String commoditiesAdditionalTerms;
		private final String creditAdditionalTerms;
		private final String interestRateAdditionalTerms;
		private final String digitalAssetAdditionalTerms;
		
		protected TransactionAdditionalTermsImpl(TransactionAdditionalTerms.TransactionAdditionalTermsBuilder builder) {
			this.equityAdditionalTerms = ofNullable(builder.getEquityAdditionalTerms()).map(f->f.build()).orElse(null);
			this.foreignExchangeAdditionalTerms = ofNullable(builder.getForeignExchangeAdditionalTerms()).map(f->f.build()).orElse(null);
			this.commoditiesAdditionalTerms = builder.getCommoditiesAdditionalTerms();
			this.creditAdditionalTerms = builder.getCreditAdditionalTerms();
			this.interestRateAdditionalTerms = builder.getInterestRateAdditionalTerms();
			this.digitalAssetAdditionalTerms = builder.getDigitalAssetAdditionalTerms();
		}
		
		@Override
		@RosettaAttribute("equityAdditionalTerms")
		public EquityAdditionalTerms getEquityAdditionalTerms() {
			return equityAdditionalTerms;
		}
		
		@Override
		@RosettaAttribute("foreignExchangeAdditionalTerms")
		public FxAdditionalTerms getForeignExchangeAdditionalTerms() {
			return foreignExchangeAdditionalTerms;
		}
		
		@Override
		@RosettaAttribute("commoditiesAdditionalTerms")
		public String getCommoditiesAdditionalTerms() {
			return commoditiesAdditionalTerms;
		}
		
		@Override
		@RosettaAttribute("creditAdditionalTerms")
		public String getCreditAdditionalTerms() {
			return creditAdditionalTerms;
		}
		
		@Override
		@RosettaAttribute("interestRateAdditionalTerms")
		public String getInterestRateAdditionalTerms() {
			return interestRateAdditionalTerms;
		}
		
		@Override
		@RosettaAttribute("digitalAssetAdditionalTerms")
		public String getDigitalAssetAdditionalTerms() {
			return digitalAssetAdditionalTerms;
		}
		
		@Override
		public TransactionAdditionalTerms build() {
			return this;
		}
		
		@Override
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder toBuilder() {
			TransactionAdditionalTerms.TransactionAdditionalTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TransactionAdditionalTerms.TransactionAdditionalTermsBuilder builder) {
			ofNullable(getEquityAdditionalTerms()).ifPresent(builder::setEquityAdditionalTerms);
			ofNullable(getForeignExchangeAdditionalTerms()).ifPresent(builder::setForeignExchangeAdditionalTerms);
			ofNullable(getCommoditiesAdditionalTerms()).ifPresent(builder::setCommoditiesAdditionalTerms);
			ofNullable(getCreditAdditionalTerms()).ifPresent(builder::setCreditAdditionalTerms);
			ofNullable(getInterestRateAdditionalTerms()).ifPresent(builder::setInterestRateAdditionalTerms);
			ofNullable(getDigitalAssetAdditionalTerms()).ifPresent(builder::setDigitalAssetAdditionalTerms);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransactionAdditionalTerms _that = getType().cast(o);
		
			if (!Objects.equals(equityAdditionalTerms, _that.getEquityAdditionalTerms())) return false;
			if (!Objects.equals(foreignExchangeAdditionalTerms, _that.getForeignExchangeAdditionalTerms())) return false;
			if (!Objects.equals(commoditiesAdditionalTerms, _that.getCommoditiesAdditionalTerms())) return false;
			if (!Objects.equals(creditAdditionalTerms, _that.getCreditAdditionalTerms())) return false;
			if (!Objects.equals(interestRateAdditionalTerms, _that.getInterestRateAdditionalTerms())) return false;
			if (!Objects.equals(digitalAssetAdditionalTerms, _that.getDigitalAssetAdditionalTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (equityAdditionalTerms != null ? equityAdditionalTerms.hashCode() : 0);
			_result = 31 * _result + (foreignExchangeAdditionalTerms != null ? foreignExchangeAdditionalTerms.hashCode() : 0);
			_result = 31 * _result + (commoditiesAdditionalTerms != null ? commoditiesAdditionalTerms.hashCode() : 0);
			_result = 31 * _result + (creditAdditionalTerms != null ? creditAdditionalTerms.hashCode() : 0);
			_result = 31 * _result + (interestRateAdditionalTerms != null ? interestRateAdditionalTerms.hashCode() : 0);
			_result = 31 * _result + (digitalAssetAdditionalTerms != null ? digitalAssetAdditionalTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransactionAdditionalTerms {" +
				"equityAdditionalTerms=" + this.equityAdditionalTerms + ", " +
				"foreignExchangeAdditionalTerms=" + this.foreignExchangeAdditionalTerms + ", " +
				"commoditiesAdditionalTerms=" + this.commoditiesAdditionalTerms + ", " +
				"creditAdditionalTerms=" + this.creditAdditionalTerms + ", " +
				"interestRateAdditionalTerms=" + this.interestRateAdditionalTerms + ", " +
				"digitalAssetAdditionalTerms=" + this.digitalAssetAdditionalTerms +
			'}';
		}
	}

	/*********************** Builder Implementation of TransactionAdditionalTerms  ***********************/
	class TransactionAdditionalTermsBuilderImpl implements TransactionAdditionalTerms.TransactionAdditionalTermsBuilder {
	
		protected EquityAdditionalTerms.EquityAdditionalTermsBuilder equityAdditionalTerms;
		protected FxAdditionalTerms.FxAdditionalTermsBuilder foreignExchangeAdditionalTerms;
		protected String commoditiesAdditionalTerms;
		protected String creditAdditionalTerms;
		protected String interestRateAdditionalTerms;
		protected String digitalAssetAdditionalTerms;
	
		public TransactionAdditionalTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("equityAdditionalTerms")
		public EquityAdditionalTerms.EquityAdditionalTermsBuilder getEquityAdditionalTerms() {
			return equityAdditionalTerms;
		}
		
		@Override
		public EquityAdditionalTerms.EquityAdditionalTermsBuilder getOrCreateEquityAdditionalTerms() {
			EquityAdditionalTerms.EquityAdditionalTermsBuilder result;
			if (equityAdditionalTerms!=null) {
				result = equityAdditionalTerms;
			}
			else {
				result = equityAdditionalTerms = EquityAdditionalTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("foreignExchangeAdditionalTerms")
		public FxAdditionalTerms.FxAdditionalTermsBuilder getForeignExchangeAdditionalTerms() {
			return foreignExchangeAdditionalTerms;
		}
		
		@Override
		public FxAdditionalTerms.FxAdditionalTermsBuilder getOrCreateForeignExchangeAdditionalTerms() {
			FxAdditionalTerms.FxAdditionalTermsBuilder result;
			if (foreignExchangeAdditionalTerms!=null) {
				result = foreignExchangeAdditionalTerms;
			}
			else {
				result = foreignExchangeAdditionalTerms = FxAdditionalTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("commoditiesAdditionalTerms")
		public String getCommoditiesAdditionalTerms() {
			return commoditiesAdditionalTerms;
		}
		
		@Override
		@RosettaAttribute("creditAdditionalTerms")
		public String getCreditAdditionalTerms() {
			return creditAdditionalTerms;
		}
		
		@Override
		@RosettaAttribute("interestRateAdditionalTerms")
		public String getInterestRateAdditionalTerms() {
			return interestRateAdditionalTerms;
		}
		
		@Override
		@RosettaAttribute("digitalAssetAdditionalTerms")
		public String getDigitalAssetAdditionalTerms() {
			return digitalAssetAdditionalTerms;
		}
		
	
		@Override
		@RosettaAttribute("equityAdditionalTerms")
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setEquityAdditionalTerms(EquityAdditionalTerms equityAdditionalTerms) {
			this.equityAdditionalTerms = equityAdditionalTerms==null?null:equityAdditionalTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("foreignExchangeAdditionalTerms")
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setForeignExchangeAdditionalTerms(FxAdditionalTerms foreignExchangeAdditionalTerms) {
			this.foreignExchangeAdditionalTerms = foreignExchangeAdditionalTerms==null?null:foreignExchangeAdditionalTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("commoditiesAdditionalTerms")
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setCommoditiesAdditionalTerms(String commoditiesAdditionalTerms) {
			this.commoditiesAdditionalTerms = commoditiesAdditionalTerms==null?null:commoditiesAdditionalTerms;
			return this;
		}
		@Override
		@RosettaAttribute("creditAdditionalTerms")
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setCreditAdditionalTerms(String creditAdditionalTerms) {
			this.creditAdditionalTerms = creditAdditionalTerms==null?null:creditAdditionalTerms;
			return this;
		}
		@Override
		@RosettaAttribute("interestRateAdditionalTerms")
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setInterestRateAdditionalTerms(String interestRateAdditionalTerms) {
			this.interestRateAdditionalTerms = interestRateAdditionalTerms==null?null:interestRateAdditionalTerms;
			return this;
		}
		@Override
		@RosettaAttribute("digitalAssetAdditionalTerms")
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder setDigitalAssetAdditionalTerms(String digitalAssetAdditionalTerms) {
			this.digitalAssetAdditionalTerms = digitalAssetAdditionalTerms==null?null:digitalAssetAdditionalTerms;
			return this;
		}
		
		@Override
		public TransactionAdditionalTerms build() {
			return new TransactionAdditionalTerms.TransactionAdditionalTermsImpl(this);
		}
		
		@Override
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder prune() {
			if (equityAdditionalTerms!=null && !equityAdditionalTerms.prune().hasData()) equityAdditionalTerms = null;
			if (foreignExchangeAdditionalTerms!=null && !foreignExchangeAdditionalTerms.prune().hasData()) foreignExchangeAdditionalTerms = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getEquityAdditionalTerms()!=null && getEquityAdditionalTerms().hasData()) return true;
			if (getForeignExchangeAdditionalTerms()!=null && getForeignExchangeAdditionalTerms().hasData()) return true;
			if (getCommoditiesAdditionalTerms()!=null) return true;
			if (getCreditAdditionalTerms()!=null) return true;
			if (getInterestRateAdditionalTerms()!=null) return true;
			if (getDigitalAssetAdditionalTerms()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransactionAdditionalTerms.TransactionAdditionalTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TransactionAdditionalTerms.TransactionAdditionalTermsBuilder o = (TransactionAdditionalTerms.TransactionAdditionalTermsBuilder) other;
			
			merger.mergeRosetta(getEquityAdditionalTerms(), o.getEquityAdditionalTerms(), this::setEquityAdditionalTerms);
			merger.mergeRosetta(getForeignExchangeAdditionalTerms(), o.getForeignExchangeAdditionalTerms(), this::setForeignExchangeAdditionalTerms);
			
			merger.mergeBasic(getCommoditiesAdditionalTerms(), o.getCommoditiesAdditionalTerms(), this::setCommoditiesAdditionalTerms);
			merger.mergeBasic(getCreditAdditionalTerms(), o.getCreditAdditionalTerms(), this::setCreditAdditionalTerms);
			merger.mergeBasic(getInterestRateAdditionalTerms(), o.getInterestRateAdditionalTerms(), this::setInterestRateAdditionalTerms);
			merger.mergeBasic(getDigitalAssetAdditionalTerms(), o.getDigitalAssetAdditionalTerms(), this::setDigitalAssetAdditionalTerms);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransactionAdditionalTerms _that = getType().cast(o);
		
			if (!Objects.equals(equityAdditionalTerms, _that.getEquityAdditionalTerms())) return false;
			if (!Objects.equals(foreignExchangeAdditionalTerms, _that.getForeignExchangeAdditionalTerms())) return false;
			if (!Objects.equals(commoditiesAdditionalTerms, _that.getCommoditiesAdditionalTerms())) return false;
			if (!Objects.equals(creditAdditionalTerms, _that.getCreditAdditionalTerms())) return false;
			if (!Objects.equals(interestRateAdditionalTerms, _that.getInterestRateAdditionalTerms())) return false;
			if (!Objects.equals(digitalAssetAdditionalTerms, _that.getDigitalAssetAdditionalTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (equityAdditionalTerms != null ? equityAdditionalTerms.hashCode() : 0);
			_result = 31 * _result + (foreignExchangeAdditionalTerms != null ? foreignExchangeAdditionalTerms.hashCode() : 0);
			_result = 31 * _result + (commoditiesAdditionalTerms != null ? commoditiesAdditionalTerms.hashCode() : 0);
			_result = 31 * _result + (creditAdditionalTerms != null ? creditAdditionalTerms.hashCode() : 0);
			_result = 31 * _result + (interestRateAdditionalTerms != null ? interestRateAdditionalTerms.hashCode() : 0);
			_result = 31 * _result + (digitalAssetAdditionalTerms != null ? digitalAssetAdditionalTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransactionAdditionalTermsBuilder {" +
				"equityAdditionalTerms=" + this.equityAdditionalTerms + ", " +
				"foreignExchangeAdditionalTerms=" + this.foreignExchangeAdditionalTerms + ", " +
				"commoditiesAdditionalTerms=" + this.commoditiesAdditionalTerms + ", " +
				"creditAdditionalTerms=" + this.creditAdditionalTerms + ", " +
				"interestRateAdditionalTerms=" + this.interestRateAdditionalTerms + ", " +
				"digitalAssetAdditionalTerms=" + this.digitalAssetAdditionalTerms +
			'}';
		}
	}
}
