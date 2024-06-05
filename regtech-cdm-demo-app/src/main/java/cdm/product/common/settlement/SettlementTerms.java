package cdm.product.common.settlement;

import cdm.product.common.settlement.CashSettlementTerms;
import cdm.product.common.settlement.PhysicalSettlementTerms;
import cdm.product.common.settlement.SettlementBase;
import cdm.product.common.settlement.SettlementBase.SettlementBaseBuilder;
import cdm.product.common.settlement.SettlementBase.SettlementBaseBuilderImpl;
import cdm.product.common.settlement.SettlementBase.SettlementBaseImpl;
import cdm.product.common.settlement.SettlementCentreEnum;
import cdm.product.common.settlement.SettlementDate;
import cdm.product.common.settlement.SettlementProvision;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.common.settlement.SettlementTerms.SettlementTermsBuilder;
import cdm.product.common.settlement.SettlementTerms.SettlementTermsBuilderImpl;
import cdm.product.common.settlement.SettlementTerms.SettlementTermsImpl;
import cdm.product.common.settlement.SettlementTypeEnum;
import cdm.product.common.settlement.StandardSettlementStyleEnum;
import cdm.product.common.settlement.TransferSettlementEnum;
import cdm.product.common.settlement.meta.SettlementTermsMeta;
import com.google.common.collect.ImmutableList;
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
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies the settlement terms, which can either be cash, physical, or fx-based cash-settlement. This class can be used for the settlement of options and forwards, cash transactions (e.g. securities or foreign exchange), or in case of credit event.
 * @version ${project.version}
 */
@RosettaDataType(value="SettlementTerms", builder=SettlementTerms.SettlementTermsBuilderImpl.class, version="${project.version}")
public interface SettlementTerms extends SettlementBase {

	SettlementTermsMeta metaData = new SettlementTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the parameters associated with the cash settlement procedure.
	 */
	List<? extends CashSettlementTerms> getCashSettlementTerms();
	/**
	 * Specifies the physical settlement terms which apply to the transaction.
	 */
	PhysicalSettlementTerms getPhysicalSettlementTerms();

	/*********************** Build Methods  ***********************/
	SettlementTerms build();
	
	SettlementTerms.SettlementTermsBuilder toBuilder();
	
	static SettlementTerms.SettlementTermsBuilder builder() {
		return new SettlementTerms.SettlementTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SettlementTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SettlementTerms> getType() {
		return SettlementTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("settlementType"), SettlementTypeEnum.class, getSettlementType(), this);
		processor.processBasic(path.newSubPath("transferSettlementType"), TransferSettlementEnum.class, getTransferSettlementType(), this);
		processRosetta(path.newSubPath("settlementCurrency"), processor, FieldWithMetaString.class, getSettlementCurrency());
		processRosetta(path.newSubPath("settlementDate"), processor, SettlementDate.class, getSettlementDate());
		processor.processBasic(path.newSubPath("settlementCentre"), SettlementCentreEnum.class, getSettlementCentre(), this);
		processRosetta(path.newSubPath("settlementProvision"), processor, SettlementProvision.class, getSettlementProvision());
		processor.processBasic(path.newSubPath("standardSettlementStyle"), StandardSettlementStyleEnum.class, getStandardSettlementStyle(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
		processRosetta(path.newSubPath("cashSettlementTerms"), processor, CashSettlementTerms.class, getCashSettlementTerms());
		processRosetta(path.newSubPath("physicalSettlementTerms"), processor, PhysicalSettlementTerms.class, getPhysicalSettlementTerms());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SettlementTermsBuilder extends SettlementTerms, SettlementBase.SettlementBaseBuilder, RosettaModelObjectBuilder {
		CashSettlementTerms.CashSettlementTermsBuilder getOrCreateCashSettlementTerms(int _index);
		List<? extends CashSettlementTerms.CashSettlementTermsBuilder> getCashSettlementTerms();
		PhysicalSettlementTerms.PhysicalSettlementTermsBuilder getOrCreatePhysicalSettlementTerms();
		PhysicalSettlementTerms.PhysicalSettlementTermsBuilder getPhysicalSettlementTerms();
		SettlementTerms.SettlementTermsBuilder setSettlementType(SettlementTypeEnum settlementType);
		SettlementTerms.SettlementTermsBuilder setTransferSettlementType(TransferSettlementEnum transferSettlementType);
		SettlementTerms.SettlementTermsBuilder setSettlementCurrency(FieldWithMetaString settlementCurrency0);
		SettlementTerms.SettlementTermsBuilder setSettlementCurrencyValue(String settlementCurrency1);
		SettlementTerms.SettlementTermsBuilder setSettlementDate(SettlementDate settlementDate);
		SettlementTerms.SettlementTermsBuilder setSettlementCentre(SettlementCentreEnum settlementCentre);
		SettlementTerms.SettlementTermsBuilder setSettlementProvision(SettlementProvision settlementProvision);
		SettlementTerms.SettlementTermsBuilder setStandardSettlementStyle(StandardSettlementStyleEnum standardSettlementStyle);
		SettlementTerms.SettlementTermsBuilder setMeta(MetaFields meta);
		SettlementTerms.SettlementTermsBuilder addCashSettlementTerms(CashSettlementTerms cashSettlementTerms0);
		SettlementTerms.SettlementTermsBuilder addCashSettlementTerms(CashSettlementTerms cashSettlementTerms1, int _idx);
		SettlementTerms.SettlementTermsBuilder addCashSettlementTerms(List<? extends CashSettlementTerms> cashSettlementTerms2);
		SettlementTerms.SettlementTermsBuilder setCashSettlementTerms(List<? extends CashSettlementTerms> cashSettlementTerms3);
		SettlementTerms.SettlementTermsBuilder setPhysicalSettlementTerms(PhysicalSettlementTerms physicalSettlementTerms);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("settlementType"), SettlementTypeEnum.class, getSettlementType(), this);
			processor.processBasic(path.newSubPath("transferSettlementType"), TransferSettlementEnum.class, getTransferSettlementType(), this);
			processRosetta(path.newSubPath("settlementCurrency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getSettlementCurrency());
			processRosetta(path.newSubPath("settlementDate"), processor, SettlementDate.SettlementDateBuilder.class, getSettlementDate());
			processor.processBasic(path.newSubPath("settlementCentre"), SettlementCentreEnum.class, getSettlementCentre(), this);
			processRosetta(path.newSubPath("settlementProvision"), processor, SettlementProvision.SettlementProvisionBuilder.class, getSettlementProvision());
			processor.processBasic(path.newSubPath("standardSettlementStyle"), StandardSettlementStyleEnum.class, getStandardSettlementStyle(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
			processRosetta(path.newSubPath("cashSettlementTerms"), processor, CashSettlementTerms.CashSettlementTermsBuilder.class, getCashSettlementTerms());
			processRosetta(path.newSubPath("physicalSettlementTerms"), processor, PhysicalSettlementTerms.PhysicalSettlementTermsBuilder.class, getPhysicalSettlementTerms());
		}
		

		SettlementTerms.SettlementTermsBuilder prune();
	}

	/*********************** Immutable Implementation of SettlementTerms  ***********************/
	class SettlementTermsImpl extends SettlementBase.SettlementBaseImpl implements SettlementTerms {
		private final List<? extends CashSettlementTerms> cashSettlementTerms;
		private final PhysicalSettlementTerms physicalSettlementTerms;
		
		protected SettlementTermsImpl(SettlementTerms.SettlementTermsBuilder builder) {
			super(builder);
			this.cashSettlementTerms = ofNullable(builder.getCashSettlementTerms()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.physicalSettlementTerms = ofNullable(builder.getPhysicalSettlementTerms()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("cashSettlementTerms")
		public List<? extends CashSettlementTerms> getCashSettlementTerms() {
			return cashSettlementTerms;
		}
		
		@Override
		@RosettaAttribute("physicalSettlementTerms")
		public PhysicalSettlementTerms getPhysicalSettlementTerms() {
			return physicalSettlementTerms;
		}
		
		@Override
		public SettlementTerms build() {
			return this;
		}
		
		@Override
		public SettlementTerms.SettlementTermsBuilder toBuilder() {
			SettlementTerms.SettlementTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SettlementTerms.SettlementTermsBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getCashSettlementTerms()).ifPresent(builder::setCashSettlementTerms);
			ofNullable(getPhysicalSettlementTerms()).ifPresent(builder::setPhysicalSettlementTerms);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			SettlementTerms _that = getType().cast(o);
		
			if (!ListEquals.listEquals(cashSettlementTerms, _that.getCashSettlementTerms())) return false;
			if (!Objects.equals(physicalSettlementTerms, _that.getPhysicalSettlementTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (cashSettlementTerms != null ? cashSettlementTerms.hashCode() : 0);
			_result = 31 * _result + (physicalSettlementTerms != null ? physicalSettlementTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementTerms {" +
				"cashSettlementTerms=" + this.cashSettlementTerms + ", " +
				"physicalSettlementTerms=" + this.physicalSettlementTerms +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of SettlementTerms  ***********************/
	class SettlementTermsBuilderImpl extends SettlementBase.SettlementBaseBuilderImpl  implements SettlementTerms.SettlementTermsBuilder {
	
		protected List<CashSettlementTerms.CashSettlementTermsBuilder> cashSettlementTerms = new ArrayList<>();
		protected PhysicalSettlementTerms.PhysicalSettlementTermsBuilder physicalSettlementTerms;
	
		public SettlementTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("cashSettlementTerms")
		public List<? extends CashSettlementTerms.CashSettlementTermsBuilder> getCashSettlementTerms() {
			return cashSettlementTerms;
		}
		
		public CashSettlementTerms.CashSettlementTermsBuilder getOrCreateCashSettlementTerms(int _index) {
		
			if (cashSettlementTerms==null) {
				this.cashSettlementTerms = new ArrayList<>();
			}
			CashSettlementTerms.CashSettlementTermsBuilder result;
			return getIndex(cashSettlementTerms, _index, () -> {
						CashSettlementTerms.CashSettlementTermsBuilder newCashSettlementTerms = CashSettlementTerms.builder();
						return newCashSettlementTerms;
					});
		}
		
		@Override
		@RosettaAttribute("physicalSettlementTerms")
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder getPhysicalSettlementTerms() {
			return physicalSettlementTerms;
		}
		
		@Override
		public PhysicalSettlementTerms.PhysicalSettlementTermsBuilder getOrCreatePhysicalSettlementTerms() {
			PhysicalSettlementTerms.PhysicalSettlementTermsBuilder result;
			if (physicalSettlementTerms!=null) {
				result = physicalSettlementTerms;
			}
			else {
				result = physicalSettlementTerms = PhysicalSettlementTerms.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("settlementType")
		public SettlementTerms.SettlementTermsBuilder setSettlementType(SettlementTypeEnum settlementType) {
			this.settlementType = settlementType==null?null:settlementType;
			return this;
		}
		@Override
		@RosettaAttribute("transferSettlementType")
		public SettlementTerms.SettlementTermsBuilder setTransferSettlementType(TransferSettlementEnum transferSettlementType) {
			this.transferSettlementType = transferSettlementType==null?null:transferSettlementType;
			return this;
		}
		@Override
		@RosettaAttribute("settlementCurrency")
		public SettlementTerms.SettlementTermsBuilder setSettlementCurrency(FieldWithMetaString settlementCurrency) {
			this.settlementCurrency = settlementCurrency==null?null:settlementCurrency.toBuilder();
			return this;
		}
		@Override
		public SettlementTerms.SettlementTermsBuilder setSettlementCurrencyValue(String settlementCurrency) {
			this.getOrCreateSettlementCurrency().setValue(settlementCurrency);
			return this;
		}
		@Override
		@RosettaAttribute("settlementDate")
		public SettlementTerms.SettlementTermsBuilder setSettlementDate(SettlementDate settlementDate) {
			this.settlementDate = settlementDate==null?null:settlementDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementCentre")
		public SettlementTerms.SettlementTermsBuilder setSettlementCentre(SettlementCentreEnum settlementCentre) {
			this.settlementCentre = settlementCentre==null?null:settlementCentre;
			return this;
		}
		@Override
		@RosettaAttribute("settlementProvision")
		public SettlementTerms.SettlementTermsBuilder setSettlementProvision(SettlementProvision settlementProvision) {
			this.settlementProvision = settlementProvision==null?null:settlementProvision.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("standardSettlementStyle")
		public SettlementTerms.SettlementTermsBuilder setStandardSettlementStyle(StandardSettlementStyleEnum standardSettlementStyle) {
			this.standardSettlementStyle = standardSettlementStyle==null?null:standardSettlementStyle;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public SettlementTerms.SettlementTermsBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		@Override
		public SettlementTerms.SettlementTermsBuilder addCashSettlementTerms(CashSettlementTerms cashSettlementTerms) {
			if (cashSettlementTerms!=null) this.cashSettlementTerms.add(cashSettlementTerms.toBuilder());
			return this;
		}
		
		@Override
		public SettlementTerms.SettlementTermsBuilder addCashSettlementTerms(CashSettlementTerms cashSettlementTerms, int _idx) {
			getIndex(this.cashSettlementTerms, _idx, () -> cashSettlementTerms.toBuilder());
			return this;
		}
		@Override 
		public SettlementTerms.SettlementTermsBuilder addCashSettlementTerms(List<? extends CashSettlementTerms> cashSettlementTermss) {
			if (cashSettlementTermss != null) {
				for (CashSettlementTerms toAdd : cashSettlementTermss) {
					this.cashSettlementTerms.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("cashSettlementTerms")
		public SettlementTerms.SettlementTermsBuilder setCashSettlementTerms(List<? extends CashSettlementTerms> cashSettlementTermss) {
			if (cashSettlementTermss == null)  {
				this.cashSettlementTerms = new ArrayList<>();
			}
			else {
				this.cashSettlementTerms = cashSettlementTermss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("physicalSettlementTerms")
		public SettlementTerms.SettlementTermsBuilder setPhysicalSettlementTerms(PhysicalSettlementTerms physicalSettlementTerms) {
			this.physicalSettlementTerms = physicalSettlementTerms==null?null:physicalSettlementTerms.toBuilder();
			return this;
		}
		
		@Override
		public SettlementTerms build() {
			return new SettlementTerms.SettlementTermsImpl(this);
		}
		
		@Override
		public SettlementTerms.SettlementTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementTerms.SettlementTermsBuilder prune() {
			super.prune();
			cashSettlementTerms = cashSettlementTerms.stream().filter(b->b!=null).<CashSettlementTerms.CashSettlementTermsBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (physicalSettlementTerms!=null && !physicalSettlementTerms.prune().hasData()) physicalSettlementTerms = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getCashSettlementTerms()!=null && getCashSettlementTerms().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getPhysicalSettlementTerms()!=null && getPhysicalSettlementTerms().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SettlementTerms.SettlementTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			SettlementTerms.SettlementTermsBuilder o = (SettlementTerms.SettlementTermsBuilder) other;
			
			merger.mergeRosetta(getCashSettlementTerms(), o.getCashSettlementTerms(), this::getOrCreateCashSettlementTerms);
			merger.mergeRosetta(getPhysicalSettlementTerms(), o.getPhysicalSettlementTerms(), this::setPhysicalSettlementTerms);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			SettlementTerms _that = getType().cast(o);
		
			if (!ListEquals.listEquals(cashSettlementTerms, _that.getCashSettlementTerms())) return false;
			if (!Objects.equals(physicalSettlementTerms, _that.getPhysicalSettlementTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (cashSettlementTerms != null ? cashSettlementTerms.hashCode() : 0);
			_result = 31 * _result + (physicalSettlementTerms != null ? physicalSettlementTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SettlementTermsBuilder {" +
				"cashSettlementTerms=" + this.cashSettlementTerms + ", " +
				"physicalSettlementTerms=" + this.physicalSettlementTerms +
			'}' + " " + super.toString();
		}
	}
}
