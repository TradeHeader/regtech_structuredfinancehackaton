package cdm.event.common;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.math.NonNegativeQuantity;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.identifier.metafields.FieldWithMetaIdentifier;
import cdm.base.staticdata.identifier.metafields.FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder;
import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.event.common.TransferBase;
import cdm.event.common.TransferBase.TransferBaseBuilder;
import cdm.event.common.TransferBase.TransferBaseBuilderImpl;
import cdm.event.common.TransferBase.TransferBaseImpl;
import cdm.event.common.meta.TransferBaseMeta;
import cdm.observable.asset.Observable;
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
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="TransferBase", builder=TransferBase.TransferBaseBuilderImpl.class, version="${project.version}")
public interface TransferBase extends RosettaModelObject {

	TransferBaseMeta metaData = new TransferBaseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents a unique reference to the transfer.
	 */
	List<? extends FieldWithMetaIdentifier> getIdentifier();
	/**
	 * Represents the amount of the asset to be transferred.
	 */
	NonNegativeQuantity getQuantity();
	/**
	 * Represents the object that is subject to the transfer, it could be an asset or a reference.
	 */
	Observable getObservable();
	/**
	 * Represents the parties to the transfer and their role.
	 */
	PartyReferencePayerReceiver getPayerReceiver();
	/**
	 * Represents the date on which the transfer to due.
	 */
	AdjustableOrAdjustedOrRelativeDate getSettlementDate();

	/*********************** Build Methods  ***********************/
	TransferBase build();
	
	TransferBase.TransferBaseBuilder toBuilder();
	
	static TransferBase.TransferBaseBuilder builder() {
		return new TransferBase.TransferBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TransferBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TransferBase> getType() {
		return TransferBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("identifier"), processor, FieldWithMetaIdentifier.class, getIdentifier());
		processRosetta(path.newSubPath("quantity"), processor, NonNegativeQuantity.class, getQuantity());
		processRosetta(path.newSubPath("observable"), processor, Observable.class, getObservable());
		processRosetta(path.newSubPath("payerReceiver"), processor, PartyReferencePayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("settlementDate"), processor, AdjustableOrAdjustedOrRelativeDate.class, getSettlementDate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface TransferBaseBuilder extends TransferBase, RosettaModelObjectBuilder {
		FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder getOrCreateIdentifier(int _index);
		List<? extends FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder> getIdentifier();
		NonNegativeQuantity.NonNegativeQuantityBuilder getOrCreateQuantity();
		NonNegativeQuantity.NonNegativeQuantityBuilder getQuantity();
		Observable.ObservableBuilder getOrCreateObservable();
		Observable.ObservableBuilder getObservable();
		PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder getOrCreatePayerReceiver();
		PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder getPayerReceiver();
		AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder getOrCreateSettlementDate();
		AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder getSettlementDate();
		TransferBase.TransferBaseBuilder addIdentifier(FieldWithMetaIdentifier identifier0);
		TransferBase.TransferBaseBuilder addIdentifier(FieldWithMetaIdentifier identifier1, int _idx);
		TransferBase.TransferBaseBuilder addIdentifierValue(Identifier identifier2);
		TransferBase.TransferBaseBuilder addIdentifierValue(Identifier identifier3, int _idx);
		TransferBase.TransferBaseBuilder addIdentifier(List<? extends FieldWithMetaIdentifier> identifier4);
		TransferBase.TransferBaseBuilder setIdentifier(List<? extends FieldWithMetaIdentifier> identifier5);
		TransferBase.TransferBaseBuilder addIdentifierValue(List<? extends Identifier> identifier6);
		TransferBase.TransferBaseBuilder setIdentifierValue(List<? extends Identifier> identifier7);
		TransferBase.TransferBaseBuilder setQuantity(NonNegativeQuantity quantity);
		TransferBase.TransferBaseBuilder setObservable(Observable observable);
		TransferBase.TransferBaseBuilder setPayerReceiver(PartyReferencePayerReceiver payerReceiver);
		TransferBase.TransferBaseBuilder setSettlementDate(AdjustableOrAdjustedOrRelativeDate settlementDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("identifier"), processor, FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder.class, getIdentifier());
			processRosetta(path.newSubPath("quantity"), processor, NonNegativeQuantity.NonNegativeQuantityBuilder.class, getQuantity());
			processRosetta(path.newSubPath("observable"), processor, Observable.ObservableBuilder.class, getObservable());
			processRosetta(path.newSubPath("payerReceiver"), processor, PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("settlementDate"), processor, AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder.class, getSettlementDate());
		}
		

		TransferBase.TransferBaseBuilder prune();
	}

	/*********************** Immutable Implementation of TransferBase  ***********************/
	class TransferBaseImpl implements TransferBase {
		private final List<? extends FieldWithMetaIdentifier> identifier;
		private final NonNegativeQuantity quantity;
		private final Observable observable;
		private final PartyReferencePayerReceiver payerReceiver;
		private final AdjustableOrAdjustedOrRelativeDate settlementDate;
		
		protected TransferBaseImpl(TransferBase.TransferBaseBuilder builder) {
			this.identifier = ofNullable(builder.getIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.quantity = ofNullable(builder.getQuantity()).map(f->f.build()).orElse(null);
			this.observable = ofNullable(builder.getObservable()).map(f->f.build()).orElse(null);
			this.payerReceiver = ofNullable(builder.getPayerReceiver()).map(f->f.build()).orElse(null);
			this.settlementDate = ofNullable(builder.getSettlementDate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("identifier")
		public List<? extends FieldWithMetaIdentifier> getIdentifier() {
			return identifier;
		}
		
		@Override
		@RosettaAttribute("quantity")
		public NonNegativeQuantity getQuantity() {
			return quantity;
		}
		
		@Override
		@RosettaAttribute("observable")
		public Observable getObservable() {
			return observable;
		}
		
		@Override
		@RosettaAttribute("payerReceiver")
		public PartyReferencePayerReceiver getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		@RosettaAttribute("settlementDate")
		public AdjustableOrAdjustedOrRelativeDate getSettlementDate() {
			return settlementDate;
		}
		
		@Override
		public TransferBase build() {
			return this;
		}
		
		@Override
		public TransferBase.TransferBaseBuilder toBuilder() {
			TransferBase.TransferBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TransferBase.TransferBaseBuilder builder) {
			ofNullable(getIdentifier()).ifPresent(builder::setIdentifier);
			ofNullable(getQuantity()).ifPresent(builder::setQuantity);
			ofNullable(getObservable()).ifPresent(builder::setObservable);
			ofNullable(getPayerReceiver()).ifPresent(builder::setPayerReceiver);
			ofNullable(getSettlementDate()).ifPresent(builder::setSettlementDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransferBase _that = getType().cast(o);
		
			if (!ListEquals.listEquals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(quantity, _that.getQuantity())) return false;
			if (!Objects.equals(observable, _that.getObservable())) return false;
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(settlementDate, _that.getSettlementDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			_result = 31 * _result + (observable != null ? observable.hashCode() : 0);
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (settlementDate != null ? settlementDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransferBase {" +
				"identifier=" + this.identifier + ", " +
				"quantity=" + this.quantity + ", " +
				"observable=" + this.observable + ", " +
				"payerReceiver=" + this.payerReceiver + ", " +
				"settlementDate=" + this.settlementDate +
			'}';
		}
	}

	/*********************** Builder Implementation of TransferBase  ***********************/
	class TransferBaseBuilderImpl implements TransferBase.TransferBaseBuilder {
	
		protected List<FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder> identifier = new ArrayList<>();
		protected NonNegativeQuantity.NonNegativeQuantityBuilder quantity;
		protected Observable.ObservableBuilder observable;
		protected PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder payerReceiver;
		protected AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder settlementDate;
	
		public TransferBaseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("identifier")
		public List<? extends FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder> getIdentifier() {
			return identifier;
		}
		
		public FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder getOrCreateIdentifier(int _index) {
		
			if (identifier==null) {
				this.identifier = new ArrayList<>();
			}
			FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder result;
			return getIndex(identifier, _index, () -> {
						FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder newIdentifier = FieldWithMetaIdentifier.builder();
						return newIdentifier;
					});
		}
		
		@Override
		@RosettaAttribute("quantity")
		public NonNegativeQuantity.NonNegativeQuantityBuilder getQuantity() {
			return quantity;
		}
		
		@Override
		public NonNegativeQuantity.NonNegativeQuantityBuilder getOrCreateQuantity() {
			NonNegativeQuantity.NonNegativeQuantityBuilder result;
			if (quantity!=null) {
				result = quantity;
			}
			else {
				result = quantity = NonNegativeQuantity.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("observable")
		public Observable.ObservableBuilder getObservable() {
			return observable;
		}
		
		@Override
		public Observable.ObservableBuilder getOrCreateObservable() {
			Observable.ObservableBuilder result;
			if (observable!=null) {
				result = observable;
			}
			else {
				result = observable = Observable.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("payerReceiver")
		public PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		public PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder getOrCreatePayerReceiver() {
			PartyReferencePayerReceiver.PartyReferencePayerReceiverBuilder result;
			if (payerReceiver!=null) {
				result = payerReceiver;
			}
			else {
				result = payerReceiver = PartyReferencePayerReceiver.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("settlementDate")
		public AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder getSettlementDate() {
			return settlementDate;
		}
		
		@Override
		public AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder getOrCreateSettlementDate() {
			AdjustableOrAdjustedOrRelativeDate.AdjustableOrAdjustedOrRelativeDateBuilder result;
			if (settlementDate!=null) {
				result = settlementDate;
			}
			else {
				result = settlementDate = AdjustableOrAdjustedOrRelativeDate.builder();
			}
			
			return result;
		}
	
		@Override
		public TransferBase.TransferBaseBuilder addIdentifier(FieldWithMetaIdentifier identifier) {
			if (identifier!=null) this.identifier.add(identifier.toBuilder());
			return this;
		}
		
		@Override
		public TransferBase.TransferBaseBuilder addIdentifier(FieldWithMetaIdentifier identifier, int _idx) {
			getIndex(this.identifier, _idx, () -> identifier.toBuilder());
			return this;
		}
		
		@Override
		public TransferBase.TransferBaseBuilder addIdentifierValue(Identifier identifier) {
			this.getOrCreateIdentifier(-1).setValue(identifier.toBuilder());
			return this;
		}
		
		@Override
		public TransferBase.TransferBaseBuilder addIdentifierValue(Identifier identifier, int _idx) {
			this.getOrCreateIdentifier(_idx).setValue(identifier.toBuilder());
			return this;
		}
		@Override 
		public TransferBase.TransferBaseBuilder addIdentifier(List<? extends FieldWithMetaIdentifier> identifiers) {
			if (identifiers != null) {
				for (FieldWithMetaIdentifier toAdd : identifiers) {
					this.identifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("identifier")
		public TransferBase.TransferBaseBuilder setIdentifier(List<? extends FieldWithMetaIdentifier> identifiers) {
			if (identifiers == null)  {
				this.identifier = new ArrayList<>();
			}
			else {
				this.identifier = identifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public TransferBase.TransferBaseBuilder addIdentifierValue(List<? extends Identifier> identifiers) {
			if (identifiers != null) {
				for (Identifier toAdd : identifiers) {
					this.addIdentifierValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public TransferBase.TransferBaseBuilder setIdentifierValue(List<? extends Identifier> identifiers) {
			this.identifier.clear();
			if (identifiers!=null) {
				identifiers.forEach(this::addIdentifierValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("quantity")
		public TransferBase.TransferBaseBuilder setQuantity(NonNegativeQuantity quantity) {
			this.quantity = quantity==null?null:quantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observable")
		public TransferBase.TransferBaseBuilder setObservable(Observable observable) {
			this.observable = observable==null?null:observable.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("payerReceiver")
		public TransferBase.TransferBaseBuilder setPayerReceiver(PartyReferencePayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementDate")
		public TransferBase.TransferBaseBuilder setSettlementDate(AdjustableOrAdjustedOrRelativeDate settlementDate) {
			this.settlementDate = settlementDate==null?null:settlementDate.toBuilder();
			return this;
		}
		
		@Override
		public TransferBase build() {
			return new TransferBase.TransferBaseImpl(this);
		}
		
		@Override
		public TransferBase.TransferBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransferBase.TransferBaseBuilder prune() {
			identifier = identifier.stream().filter(b->b!=null).<FieldWithMetaIdentifier.FieldWithMetaIdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (quantity!=null && !quantity.prune().hasData()) quantity = null;
			if (observable!=null && !observable.prune().hasData()) observable = null;
			if (payerReceiver!=null && !payerReceiver.prune().hasData()) payerReceiver = null;
			if (settlementDate!=null && !settlementDate.prune().hasData()) settlementDate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIdentifier()!=null && getIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getQuantity()!=null && getQuantity().hasData()) return true;
			if (getObservable()!=null && getObservable().hasData()) return true;
			if (getPayerReceiver()!=null && getPayerReceiver().hasData()) return true;
			if (getSettlementDate()!=null && getSettlementDate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TransferBase.TransferBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TransferBase.TransferBaseBuilder o = (TransferBase.TransferBaseBuilder) other;
			
			merger.mergeRosetta(getIdentifier(), o.getIdentifier(), this::getOrCreateIdentifier);
			merger.mergeRosetta(getQuantity(), o.getQuantity(), this::setQuantity);
			merger.mergeRosetta(getObservable(), o.getObservable(), this::setObservable);
			merger.mergeRosetta(getPayerReceiver(), o.getPayerReceiver(), this::setPayerReceiver);
			merger.mergeRosetta(getSettlementDate(), o.getSettlementDate(), this::setSettlementDate);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TransferBase _that = getType().cast(o);
		
			if (!ListEquals.listEquals(identifier, _that.getIdentifier())) return false;
			if (!Objects.equals(quantity, _that.getQuantity())) return false;
			if (!Objects.equals(observable, _that.getObservable())) return false;
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(settlementDate, _that.getSettlementDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (identifier != null ? identifier.hashCode() : 0);
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			_result = 31 * _result + (observable != null ? observable.hashCode() : 0);
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (settlementDate != null ? settlementDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TransferBaseBuilder {" +
				"identifier=" + this.identifier + ", " +
				"quantity=" + this.quantity + ", " +
				"observable=" + this.observable + ", " +
				"payerReceiver=" + this.payerReceiver + ", " +
				"settlementDate=" + this.settlementDate +
			'}';
		}
	}
}
